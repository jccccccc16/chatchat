package com.cjc.chatchat.entity;

public class UserPO {
    private Integer id;

    private String username;

    private String loginAcct;

    private String userPswd;

    private String name;

    private String birthday;

    private String headerPicturePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct == null ? null : loginAcct.trim();
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd == null ? null : userPswd.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath == null ? null : headerPicturePath.trim();
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", headerPicturePath='" + headerPicturePath + '\'' +
                '}';
    }
}