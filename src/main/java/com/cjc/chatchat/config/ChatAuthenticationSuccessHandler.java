package com.cjc.chatchat.config;

import com.alibaba.fastjson.JSON;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 * 登录成功之后的处理器，由于我们是ajax登录
 **/
@Component
public class ChatAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private Logger logger = LoggerFactory.getLogger(ChatAuthenticationSuccessHandler.class);

    @Resource
    private UserPOMapper userPOMapper;





    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 获取用户信息
        String loginAcct = httpServletRequest.getParameter(ChatChatConstant.ATTR_LOGIN_ACCT);

        // 将用户的登录状态设置为在线
        UserPOExample userPOExample = new UserPOExample();
        userPOExample.createCriteria().andLoginAcctEqualTo(loginAcct);
        UserPO userPO = new UserPO();
        userPO.setIsLogin(ChatChatConstant.SET_USER_LOGIN);
        userPOMapper.updateByExampleSelective(userPO,userPOExample);


        httpServletResponse.setCharacterEncoding("UTF-8");
        ResultEntity<String> resultEntity = ResultEntity.successWithData(loginAcct);
        PrintWriter out = httpServletResponse.getWriter();
        String json = JSON.toJSONString(resultEntity);
        out.write(json);
        out.flush();
        out.close();

    }
}
