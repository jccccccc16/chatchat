package com.cjc.chatchat.entity.ws;

import com.cjc.chatchat.entity.UserVO;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 * 服务器发送给客户端的信息
 * type="onlineMessage" 用户登录系统发送给客户端消息
 * type="offlineMessage" 用户离线系统发送给客户端消息
 **/
public class ResultMessage {

    public static final String TYPE_ONLINE_MESSAGE="onlineMessage";
    public static final String TYPE_OFFLINE_MESSAGE="offlineMessage";

    private boolean isSystem;
    private String type;
    private boolean isPicture;
    private UserVO fromUser;
    private Object message;

    public ResultMessage() {
    }

    public ResultMessage(boolean isSystem, String type, boolean isPicture, UserVO fromUser, Object message) {
        this.isSystem = isSystem;
        this.type = type;
        this.isPicture = isPicture;
        this.fromUser = fromUser;
        this.message = message;
    }

    public boolean getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(boolean isPicture) {
        this.isPicture = isPicture;
    }

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean system) {
        isSystem = system;
    }


    public UserVO getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserVO fromUser) {
        this.fromUser = fromUser;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
