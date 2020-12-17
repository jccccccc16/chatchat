package com.cjc.chatchat;

import com.cjc.chatchat.util.RedisUtils;
import com.cjc.chatchat.ws.ChatEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/17
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ChatEndpoint chatEndpoint;

    @Test
    public void test(){

        redisUtils.insertMessageRecord("fromLoginAcct","toLoginAcct","hello",false);
        System.out.println(redisUtils);

    }



}
