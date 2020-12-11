package com.cjc.chatchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/10
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class ChatConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 将“main”页面 映射到 “/”url

        String mainRegUrlPath="/";
        String mainViewName ="main";
        registry.addViewController(mainRegUrlPath).setViewName(mainViewName);


        // 登录页面
        String loginRegUrlPath = "/login.html";
        String loginViewName = "login";
        registry.addViewController(loginRegUrlPath).setViewName(loginViewName);


    }





}
