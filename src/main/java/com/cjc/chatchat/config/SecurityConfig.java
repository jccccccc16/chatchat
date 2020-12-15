package com.cjc.chatchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private Md5PasswordEncoded md5PasswordEncoded;

    @Resource
    private ChatAuthenticationSuccessHandler successHandler;

    @Resource
    private ChatAuthenticationFailureHandler failureHandler;

    @Resource
    private ChatAuthenticationProvider chatAuthenticationProvider;


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
                .antMatchers(HttpMethod.POST,"/user/login")
                .permitAll()
                .antMatchers("/user/login.json")
                .permitAll()
                .antMatchers("/login.html")
                .permitAll()
                .antMatchers("/user/register.json")
                .permitAll()
                .antMatchers("/user/upload/headerPicture.json")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .permitAll()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login.json")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .csrf()
                .disable();
        http.sessionManagement()
                .invalidSessionUrl("/login.html");
        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login.html");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(md5PasswordEncoded);
        auth.authenticationProvider(chatAuthenticationProvider);

    }


}
