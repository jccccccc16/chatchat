package com.cjc.chatchat.entity;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/10
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 *
 * 用户注册的VO
 **/
public class UserRegisterVO  {

    private String username;

    private String loginAcct;

    private String userPswd;

    private String repeatUserPswd;

    private String name;

    private String birthday;

    private String headerPicturePath;

    public UserRegisterVO(){

    }


    public UserRegisterVO(String username, String loginAcct, String userPswd, String repeatUserPswd, String name, String birthday, String headerPicturePath) {
        this.username = username;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.repeatUserPswd = repeatUserPswd;
        this.name = name;
        this.birthday = birthday;
        this.headerPicturePath = headerPicturePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRepeatUserPswd() {
        return repeatUserPswd;
    }

    public void setRepeatUserPswd(String repeatUserPswd) {
        this.repeatUserPswd = repeatUserPswd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

    @Override
    public String toString() {
        return "UserRegisterVO{" +
                "username='" + username + '\'' +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", repeatUserPswd='" + repeatUserPswd + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", headerPicturePath='" + headerPicturePath + '\'' +
                '}';
    }
}
