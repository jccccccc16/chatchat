package com.cjc.chatchat.service.impl;

import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/9
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserPOMapper userPOMapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 保存一个用户
     * @param userPO
     */
    @Override
    public void saveUser(UserPO userPO) {

        userPOMapper.insert(userPO);
        logger.info("保存成功");


    }

    @Override
    public UserPO getUserByLoginAcct(String loginAcct) {

        UserPOExample userPOExample = new UserPOExample();

        UserPOExample.Criteria criteria = userPOExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<UserPO> userPOList = userPOMapper.selectByExample(userPOExample);

        /**
         * 如果为空
         */
        if(userPOList==null || userPOList.size()==0){
            return null;
        }

        UserPO userPO = userPOList.get(0);
        return userPO;

    }
}
