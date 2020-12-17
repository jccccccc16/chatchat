package com.cjc.chatchat.entity;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/15
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 * 存放一行聊天记录
 **/
public class Record implements Serializable {

    public static final String TYPE_YOU="you";
    public static final String TYPE_ME="me";


    //"you" 或 "me" 用来标识是哪一方的消息

    private String type;

    // 聊天消息
    private String message;

    private boolean isPicture;

    public Record() {
    }

    public Record(String type, String message,boolean isPicture) {
        this.type = type;
        this.message = message;
        this.isPicture = isPicture;
    }

    public boolean getIsPicture() {
        return isPicture;
    }

    public void setPicture(boolean isPicture) {
        isPicture = isPicture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Record{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", isPicture=" + isPicture +
                '}';
    }
}
