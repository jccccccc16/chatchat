package com.cjc.chatchat.ws;

import com.cjc.chatchat.config.GetHttpSessionConfigurator;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserVO;
import com.cjc.chatchat.entity.ws.Message;
import com.cjc.chatchat.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.ORBInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 **/
@ServerEndpoint(value = "/user/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {


    // 用来存储当前在线的用户的chatEndpoint和对应的userVo
    // key为用户账号
//    private static Map<String,ChatEndpointUserMapper> onlineUserMap
//            = new ConcurrentHashMap<>();

    // 主要用于排序，让好友列表有一个先后顺序
    private static List<ChatEndpointUserMapper> onlineChatEndpointUserMapperList = Collections.synchronizedList(new ArrayList<>());

    //  为了方便查找用户,存储的数据与上面的list一样
    private static Map<String,ChatEndpointUserMapper> onlineChatEndpointUserMapperMap = new ConcurrentHashMap<>();


    private Logger logger = LoggerFactory.getLogger(ChatEndpoint.class);

    // 通过该对象发送给指定用户
    private Session session;

    // 用于获取当前用户名
    private HttpSession httpSession;


    /**
     * 当建立连接时调用
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){

        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;



        // 从springSecurity中获取当前登录用户
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//        SecurityUser loginUser = (SecurityUser) securityContextImpl.getAuthentication().getPrincipal();
//        UserPO originalUser = loginUser.getOriginalUser();
//        String loginAcct = originalUser.getLoginAcct();
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(originalUser,new UserVO());

        UserVO userVO = getCurrentUser();
        logger.info(userVO.toString());


        // 将当前用户放到onlineUserMap中
        ChatEndpointUserMapper mapper = new ChatEndpointUserMapper(this, userVO);
        onlineChatEndpointUserMapperList.add(mapper);
        onlineChatEndpointUserMapperMap.put(userVO.getLoginAcct(),mapper);

        // 将该用户的用户名推送给所有的客户端
        // 获取消息，包含所有在线用户的列表
        String allOnlineUserMessage = MessageUtils.getMessage(true,null, getUserVOListFromMapper());
        logger.info("allOnlineUserMessage:"+allOnlineUserMessage);

        // 仅包含当前用户信息的消息
        ArrayList<UserVO> currentUser = new ArrayList<>();
        currentUser.add(getCurrentUser());
        String currentUserMessage = MessageUtils.getMessage(true,null,currentUser);
        logger.info("currentUserMessage:"+currentUserMessage);

        // 调用广播方法
        broadcastAllUsers(allOnlineUserMessage,currentUserMessage);


    }


    private UserVO getCurrentUser(){

//        SecurityUser loginUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityContextImpl securityContextImpl = (SecurityContextImpl)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityUser loginUser = (SecurityUser) securityContextImpl.getAuthentication().getPrincipal();
        UserPO originalUser = loginUser.getOriginalUser();
        String loginAcct = originalUser.getLoginAcct();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(originalUser,userVO);
        return userVO;
    }

    /**
     * 获取onlineChatEndpointUserMapperList中的用户列表
     * @return
     */
    private List<UserVO> getUserVOListFromMapper(){

        List<UserVO> userVOList = new ArrayList<>();
        for (ChatEndpointUserMapper chatEndpointUserMapper : onlineChatEndpointUserMapperList) {
            UserVO userVO = chatEndpointUserMapper.getUserVO();
            userVOList.add(userVO);
        }
        return userVOList;
    }


    /***
     * 将message广播给所有用户,进行更新好友列表
     * 如果不是该用户的其他用户只传送该用户的message
     * 如果是该用户那么将全部用户都更新到好友列表中
     * @param allOnlineUserMessage
     * @param currentUserMessage
     */
    private void broadcastAllUsers(String allOnlineUserMessage,String currentUserMessage){

        try {
//            Set<String> onlineLoginAccts = getOnlineLoginAccts();
//            for (String loginAcct : onlineLoginAccts) {
//                ChatEndpointUserMapper mapper = onlineUserMap.get(loginAcct);
//                ChatEndpoint chatEndpoint = mapper.getChatEndpoint();
//                chatEndpoint.session.getBasicRemote().sendText(message);
//            }

            UserVO currentUser = getCurrentUser();
            String currentUserLoginAcct = currentUser.getLoginAcct();


            for (ChatEndpointUserMapper chatEndpointUserMapper : onlineChatEndpointUserMapperList) {
                String loginAcct = chatEndpointUserMapper.getUserVO().getLoginAcct();
                // 如果是当前用户
                ChatEndpoint chatEndpoint = chatEndpointUserMapper.getChatEndpoint();
                if(currentUserLoginAcct.equals(loginAcct)){
                    // 那么更新整个好友列表
                    chatEndpoint.session.getBasicRemote().sendText(allOnlineUserMessage);
                }else{
                    // 如果不是当前用户
                    // 仅将当前用户更新到好友列表
                    chatEndpoint.session.getBasicRemote().sendText(currentUserMessage);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 当接收到消息时被调用
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Message msg = objectMapper.readValue(message, Message.class);
            // 获取就接收者
            String toName = msg.getToName();

            // 获取消息数据
            String data = msg.getMessage();

            // 获取发送者
            UserVO currentUser = getCurrentUser();

            // 获取服务端发送给客户端的消息格式
            String sendMessage = MessageUtils.getMessage(false, currentUser, data);

            // 获取接受者的服务端
            ChatEndpointUserMapper chatEndpointUserMapper = onlineChatEndpointUserMapperMap.get(toName);
            ChatEndpoint chatEndpoint = chatEndpointUserMapper.getChatEndpoint();
            // 发送
            chatEndpoint.session.getBasicRemote().sendText(sendMessage);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 关闭连接时
     * @param session
     */
    @OnClose
    public void onClose(Session session){

    }




}
