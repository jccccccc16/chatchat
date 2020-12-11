package com.cjc.chatchat.entity.ws;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 * 服务器发送给客户端的信息
 **/
public class ResultMessage {

    private boolean isSystem;
    private String fromName;
    private Object message;

    public ResultMessage() {
    }

    public ResultMessage(boolean isSystem, String fromName, Object message) {
        this.isSystem = isSystem;
        this.fromName = fromName;
        this.message = message;
    }

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean system) {
        isSystem = system;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
