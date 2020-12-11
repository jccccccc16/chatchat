package com.cjc.chatchat.entity;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 * 用于好友列表
 **/
public class UserVO {

    private String username;

    private String loginAcct;

    private String name;

    private String birthday;

    private String headerPicturePath;
    // 用户上线时的时间或者是发送消息时的时间
    private String updateTime;

    private String message;

    public UserVO() {
    }


    public UserVO(String username, String loginAcct, String name, String birthday, String headerPicturePath, String updateTime, String message) {
        this.username = username;
        this.loginAcct = loginAcct;
        this.name = name;
        this.birthday = birthday;
        this.headerPicturePath = headerPicturePath;
        this.updateTime = updateTime;
        this.message = message;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", loginAcct='" + loginAcct + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", headerPicturePath='" + headerPicturePath + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
