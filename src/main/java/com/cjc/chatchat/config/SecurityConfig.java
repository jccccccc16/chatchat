package com.cjc.chatchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import sun.security.provider.MD5;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 0:14
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private Md5PasswordEncoded md5PasswordEncoded;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/js/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/user/login")
                .permitAll()
                .antMatchers("/login.html")
                .permitAll()
                .antMatchers("/user/register.json")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/main.html");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(md5PasswordEncoded);
    }

    // 做到springsecurity的加密
}
