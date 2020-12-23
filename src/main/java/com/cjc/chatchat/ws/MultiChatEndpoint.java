package com.cjc.chatchat.ws;

import com.cjc.chatchat.config.GetHttpSessionConfigurator;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserVO;
import com.cjc.chatchat.entity.ws.Message;
import com.cjc.chatchat.entity.ws.ResultMessage;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.util.ChatUtil;
import com.cjc.chatchat.util.MessageUtils;
import com.cjc.chatchat.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 * 多人聊天
 *
 *
 * 需求：
 *  当有用户打开聊天室时，该用户进入聊天室的提醒，会在其他用户的页面以提示框的形式提示，以及在左边的好友列表中展示；
 *  当由用户离开聊天室时，该用户离开聊天室的提醒，会在其他用户的页面以提示框的形式提示，而且会在列表中移出；
 **/
@ServerEndpoint(value = "/user/multi/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class MultiChatEndpoint {



    private static RedisUtils redisUtils;


    private Logger logger = LoggerFactory.getLogger(MultiChatEndpoint.class);

    // 通过该对象发送给指定用户
    private Session session;

    // 用于获取当前用户名
    private HttpSession httpSession;


    // 主要用于排序，让好友列表有一个先后顺序
    public static List<MultiChatEndpointUserMapper> onlineChatEndpointUserMapperList = Collections.synchronizedList(new ArrayList<>());

    //  为了方便查找用户,存储的数据与上面的list一样
    public static Map<String, MultiChatEndpointUserMapper> onlineChatEndpointUserMapperMap = new ConcurrentHashMap<>();


    private static UserPOMapper userPOMapper;

    @Resource
    public void setUserPOMapper(UserPOMapper userPOMapper){
        this.userPOMapper = userPOMapper;
    }


    @Autowired
    public void setRedisUtil(RedisUtils redisUtil) {
        this.redisUtils = redisUtil;
    }


    /**
     * 当建立连接时调用
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {


        // 获取该用户信息
        // 广播
        // 将“xx用户进入当前聊天室”

        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        UserVO userVO = getCurrentUser();
        logger.info(userVO.toString());


        // 将当前用户放到onlineUserMap中
        MultiChatEndpointUserMapper mapper = new MultiChatEndpointUserMapper(this, userVO);
        onlineChatEndpointUserMapperList.add(mapper);
        onlineChatEndpointUserMapperMap.put(userVO.getLoginAcct(), mapper);


        // xx 用户进入当前聊天室
        String data = userVO.getName()+ChatChatConstant.MESSAGE_USER_ENTER_MULTI_ROOM;

        // 获取消息
        String returnMessage = MessageUtils.getMessage(true, false, ResultMessage.TYPE_ONLINE_MESSAGE, null, data);


        logger.info("returnMessage:" + returnMessage);

        // 调用广播方法
        broadcastAllUsers(returnMessage);

        logger.info("向聊天室发送消息: "+returnMessage);


    }


    public UserVO getCurrentUser() {

//        SecurityUser loginUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityContextImpl securityContextImpl = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityUser loginUser = (SecurityUser) securityContextImpl.getAuthentication().getPrincipal();
        UserPO originalUser = loginUser.getOriginalUser();
        String loginAcct = originalUser.getLoginAcct();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(originalUser, userVO);
        return userVO;
    }

    /**
     * 获取onlineChatEndpointUserMapperList中的用户列表
     *
     * @return
     */
    private List<UserVO> getUserVOListFromMapper() {

        List<UserVO> userVOList = new ArrayList<>();
        for (MultiChatEndpointUserMapper chatEndpointUserMapper : onlineChatEndpointUserMapperList) {
            UserVO userVO = chatEndpointUserMapper.getUserVO();
            userVOList.add(userVO);
        }
        return userVOList;
    }


    /**
     * 广播消息
     * @param returnMessage
     */
    private void broadcastAllUsers(String returnMessage) {

        try {

            UserVO currentUser = getCurrentUser();
            String currentUserLoginAcct = currentUser.getLoginAcct();


            for (MultiChatEndpointUserMapper chatEndpointUserMapper : onlineChatEndpointUserMapperList) {
                String loginAcct = chatEndpointUserMapper.getUserVO().getLoginAcct();
                // 如果是当前用户
                MultiChatEndpoint chatEndpoint = chatEndpointUserMapper.getChatEndpoint();

                chatEndpoint.session.getBasicRemote().sendText(returnMessage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 当接收到消息时被调用
     *
     * @param message 通过message中的toName，确定接收者
     * @param session 发送者的session
     */
    @OnMessage
    public void onMessage(String message, Session session) {

        try {

            logger.info("接收到聊天室发来的message:" + message);


            Message msg = ChatUtil.stringToMessage(message);

            if(message!=null){
                throw new RuntimeException("ChatUtil.stringToMessage(message) 转换message结果为空");
            }

            // 获取就接收者的账号
            String toName = msg.getToName();

            // 获取消息数据
            String data = msg.getMessage();

            // 获取发送者
            UserVO currentUser = getCurrentUser();

            // 存储消息
            redisUtils.insertMultiMessageRecord(currentUser, getUserVOListFromMapper(), data, msg.isPicture());

            // 获取服务端发送给客户端的消息格式
            String sendMessage = MessageUtils.getMessage(false, msg.isPicture(), null, currentUser, data);

           // 广播
            broadcastAllUsers(sendMessage);

            logger.info(currentUser.getLoginAcct()+"用户"+"发送到聊天室的message:" + sendMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭连接时,关闭浏览器会调用该方法
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {

        UserVO currentUser = getCurrentUser();
        String currentLoginAcct = currentUser.getLoginAcct();

        MultiChatEndpointUserMapper currentMapper = onlineChatEndpointUserMapperMap.get(currentLoginAcct);

        // 获取退出的用户
        UserVO userVO = currentMapper.getUserVO();


        // 获取消息
        // 广播
        String message = MessageUtils.getMessage(true, false, ResultMessage.TYPE_OFFLINE_MESSAGE, currentUser, currentUser);

        broadcastAllUsers(message);

        logger.info("用户："+currentLoginAcct+" 退出聊天室");

    }

    /**
     * 该离线的用户信息发送给其他的用户，在好友列表中删除
     *
     * @param message
     */
//    private void offlineBroadcastAll(String message) {
//
//
//        try {
//            for (ChatEndpointUserMapper chatEndpointUserMapper : onlineChatEndpointUserMapperList) {
//                String loginAcct = chatEndpointUserMapper.getUserVO().getLoginAcct();
//                ChatEndpointUserMapper chatEndpointUserMapper1 = onlineChatEndpointUserMapperMap.get(loginAcct);
//                MultiChatEndpoint chatEndpoint = chatEndpointUserMapper.getChatEndpoint();
//
//                chatEndpoint.session.getBasicRemote().sendText(message);
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
