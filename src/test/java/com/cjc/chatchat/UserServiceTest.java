package com.cjc.chatchat;

import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/10
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testSave(){

        UserPO userPO = new UserPO();
        userPO.setUserPswd(" cjc1316 ");
        userPO.setLoginAcct(" cjc1316 ");
        userPO.setUsername(" cjc1316 ");
        userPO.setName(" cjc1316 ");
        userPO.setBirthday("1999-01-01");
        userPO.setHeaderPicturePath("test01");
        userService.saveUser(userPO);

    }

    public void getUserPOByLoginAcct(){

        userService.getUserByLoginAcct("cjc1316");


    }

}
