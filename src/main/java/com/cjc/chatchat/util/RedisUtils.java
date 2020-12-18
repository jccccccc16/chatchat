package com.cjc.chatchat.util;

import com.cjc.chatchat.entity.Record;
import com.cjc.chatchat.entity.UserVO;
import com.cjc.chatchat.ws.ChatEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/15
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class RedisUtils {


    @Autowired
    private RedisTemplate<String,Record> redisTemplate;



    /***
     * 插入一条消息，接收和发送方都保存
     * @param fromLoginAcct
     * @param toLoginAcct
     * @param message
     */
    public void insertMessageRecord(String fromLoginAcct,String toLoginAcct, String message,boolean isPicture){

        // 一式两份，存到redis中
        String fromKey = createKey(fromLoginAcct, toLoginAcct);
        String toKey = createKey(toLoginAcct,fromLoginAcct);
        Record fromRecord = createRecord(message, Record.TYPE_ME,isPicture);
        Record toRecord = createRecord(message, Record.TYPE_YOU,isPicture);
        listLeftPushRecord(fromKey,fromRecord);
        listLeftPushRecord(toKey,toRecord);

    }

    public void listLeftPushRecord(String key,Record value){
        redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 生成聊天记录的key，又接收方和发送方组成
     * @param fromLoginAcct
     * @param toLoginAcct
     * @return
     */
    public  String createKey(String fromLoginAcct,String toLoginAcct){
        return fromLoginAcct+"|"+toLoginAcct;

    }

    /**
     * 生成一条聊天记录，用来存储到redis
     * @param message
     * @param type
     * @return
     */
    private Record createRecord(String message,String type,boolean isPicture){
        return new Record(type,message,isPicture);
    }

    public List<Record> getRecordList(String fromLoginAcct,String toLoginAcct){


        String key = createKey(fromLoginAcct, toLoginAcct);
        List<Record> recordList = redisTemplate.opsForList().range(key, 0, 7);
        // 反转
        Collections.reverse(recordList);
        return recordList;

    }





}
