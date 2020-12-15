package com.cjc.chatchat.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/14
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 * 防止二次登录，当有用户第二次登陆时
 **/
public class UserAlreadyLoginException extends AuthenticationServiceException {
    public UserAlreadyLoginException(String msg) {
        super(msg);
    }
}
