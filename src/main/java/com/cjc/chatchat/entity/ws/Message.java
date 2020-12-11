package com.cjc.chatchat.entity.ws;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 * ws的message,客户端给服务端的message
 **/
public class Message {

    private String toName;
    private String message;

    public Message() {
    }

    public Message(String toName, String message) {
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

    @Override
    public String toString() {
        return "Message{" +
                "toName='" + toName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
