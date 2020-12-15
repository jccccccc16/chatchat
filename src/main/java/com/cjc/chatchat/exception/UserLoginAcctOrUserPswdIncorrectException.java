package com.cjc.chatchat.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/14
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 * 账号或密码错误
 **/
public class UserLoginAcctOrUserPswdIncorrectException extends AuthenticationServiceException {
    public UserLoginAcctOrUserPswdIncorrectException(String msg) {
        super(msg);
    }
}
