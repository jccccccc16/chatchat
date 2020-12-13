package com.cjc.chatchat.config;

import com.alibaba.fastjson.JSON;
import com.cjc.chatchat.util.ResultEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 * 登录成功之后的处理器，由于我们是ajax登录
 **/
@Component
public class ChatAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


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

        httpServletResponse.setCharacterEncoding("UTF-8");
        ResultEntity<Object> resultEntity = ResultEntity.successWithoutData();
        PrintWriter out = httpServletResponse.getWriter();
        String json = JSON.toJSONString(resultEntity);
        out.write(json);
        out.flush();
        out.close();

    }
}
