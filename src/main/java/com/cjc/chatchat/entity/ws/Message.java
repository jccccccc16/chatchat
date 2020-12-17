package com.cjc.chatchat.entity.ws;

import com.fasterxml.jackson.core.SerializableString;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 * ws的message,客户端给服务端的message
 **/
public class Message {


    private boolean isPicture;
    private String toName;
    private String message;

    public Message() {
    }

    public Message(String toName, String message,boolean isPicture) {
        this.isPicture = isPicture;
        this.toName = toName;
        this.message = message;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPicture() {
        return isPicture;
    }

    public void setIsPicture(boolean picture) {
        isPicture = picture;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + isPicture + '\'' +
                ", toName='" + toName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
