package com.cjc.chatchat.config;

import com.alibaba.fastjson.JSON;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.util.ResultEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/13
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 * 登录失败后处理器
 **/
@Component
public class ChatAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String loginAcct = httpServletRequest.getParameter("loginAcct");
        System.out.println("onAuthenticationFailure,loginAcct:"+loginAcct);
        httpServletResponse.setCharacterEncoding("UTF-8");

        // 获取异常信息
        String message = e.getMessage();

        // 封装异常信息
        ResultEntity<Object> resultEntity = ResultEntity.failed(message);

        PrintWriter out = httpServletResponse.getWriter();
        String json = JSON.toJSONString(resultEntity);
        out.write(json);
        out.flush();
        out.close();
    }



}
