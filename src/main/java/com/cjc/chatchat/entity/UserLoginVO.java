package com.cjc.chatchat.entity;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/10
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 *
 **/
public class UserLoginVO {

    private String loginAcct;

    private String userPswd;

    // 当remember为1时，记住他
    private String rememberMe;

    public UserLoginVO() {
    }

    public UserLoginVO(String loginAcct, String userPswd, String rememberMe) {
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.rememberMe = rememberMe;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", rememberMe='" + rememberMe + '\'' +
                '}';
    }
}
