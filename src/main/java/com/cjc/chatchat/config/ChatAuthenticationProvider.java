package com.cjc.chatchat.config;

import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.exception.UserLoginAcctOrUserPswdIncorrectException;
import com.cjc.chatchat.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/14
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class ChatAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private Md5PasswordEncoded md5PasswordEncoded;

    private Logger logger = LoggerFactory.getLogger(ChatAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        logger.info("进入ChatAuthenticationProvider");

        String loginAcct = authentication.getName();
        logger.info("authentication.getName();"+authentication.getName());
        String presentedPassword = authentication.getCredentials().toString();
        logger.info("authentication:"+authentication);
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginAcct);
        String userDetailsPassword = userDetails.getPassword();
        String encode = md5PasswordEncoded.encode(presentedPassword);
        logger.info("userDetailsPassword.equals(encode):"+userDetailsPassword.equals(encode));
        // 如果密码不正确
        if(!userDetailsPassword.equals(encode)){

            throw new UserLoginAcctOrUserPswdIncorrectException(ChatChatConstant.MESSAGE_LOGIN_FAILED);

        }

        return new UsernamePasswordAuthenticationToken(principal,authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
