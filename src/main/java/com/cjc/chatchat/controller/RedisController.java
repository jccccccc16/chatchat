package com.cjc.chatchat.controller;

import com.cjc.chatchat.entity.Record;
import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserVO;
import com.cjc.chatchat.util.RedisUtils;
import com.cjc.chatchat.util.ResultEntity;
import com.cjc.chatchat.ws.ChatEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/17
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class RedisController {

    @Resource
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(RedisController.class);




    /**
     * 获取聊天记录
     * @param toLoginAcct
     * @return
     */
    @ResponseBody
    @RequestMapping("/chat/get/message/record.json")
    public ResultEntity<List<Record>> getMessageRecordList(HttpSession httpSession, @RequestParam("toLoginAcct") String toLoginAcct){

        // 获取当前用户
        SecurityContextImpl securityContextImpl = (SecurityContextImpl)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityUser loginUser = (SecurityUser) securityContextImpl.getAuthentication().getPrincipal();
        UserPO originalUser = loginUser.getOriginalUser();
        String fromLoginAcct = originalUser.getLoginAcct();

        logger.info("fromLoginAcct:"+fromLoginAcct+","+"toLoginAcct:"+toLoginAcct);
        List<Record> recordList = redisUtils.getRecordList(fromLoginAcct,toLoginAcct);
        logger.info("从redis中查询到的聊天记录："+recordList);
        ResultEntity<List<Record>> listResultEntity = ResultEntity.successWithData(recordList);
        logger.info("封装为resultEntity"+listResultEntity);
        return listResultEntity;

    }

}
