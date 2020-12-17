package com.cjc.chatchat.entity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/15
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 * 封装聊天记录，存储在redis中
 **/
public class MessageRecord {

    private String id;

    // 聊天记录
    private List<Record> recordList;

    public MessageRecord(String id, List<Record> recordList) {
        this.id = id;
        this.recordList = recordList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String toString() {
        return "MessageRecord{" +
                "id='" + id + '\'' +
                ", recordList=" + recordList +
                '}';
    }
}
