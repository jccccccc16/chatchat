package com.cjc.chatchat.entity.ws;

import com.cjc.chatchat.entity.UserVO;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 * 服务器发送给客户端的信息
 *
 **/
public class ResultMessage {

    private boolean isSystem;
    private UserVO fromUser;
    private Object message;

    public ResultMessage() {
    }

    public ResultMessage(boolean isSystem, UserVO fromUser, Object message) {
        this.isSystem = isSystem;
        this.fromUser = fromUser;
        this.message = message;
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
}
