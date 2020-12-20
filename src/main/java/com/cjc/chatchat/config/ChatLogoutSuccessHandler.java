package com.cjc.chatchat.config;

import com.alibaba.fastjson.JSON;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.util.ChatUtil;
import com.cjc.chatchat.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/19
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class ChatLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private UserPOMapper userPOMapper;

    private Logger logger = LoggerFactory.getLogger(ChatLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        ResultEntity<String> resultEntity=null;
        try{


            String loginAcct = request.getParameter("loginAcct");

            // 将该用户的is_login字段设置为0
            UserPOExample userPOExample = new UserPOExample();
            UserPOExample.Criteria criteria = userPOExample.createCriteria();
            criteria.andLoginAcctEqualTo(loginAcct);
            UserPO userPO = new UserPO();
            userPO.setIsLogin(ChatChatConstant.SET_USER_LOGOUT);
            userPOMapper.updateByExampleSelective(userPO, userPOExample);

            logger.info("将用户: "+loginAcct+" 设置为离线状态");

            logger.info("用户： "+loginAcct+" 登出成功");

            // 跳转到主页面

            resultEntity = ResultEntity.successWithoutData();
        }catch (Exception exception){
            logger.warn("onLogoutSuccess中，清楚用户登录状态出错");
            exception.printStackTrace();
            resultEntity = ResultEntity.failed(exception.getMessage());

        }

        String json = JSON.toJSONString(resultEntity);

        response.getWriter().write(json);




    }
}
