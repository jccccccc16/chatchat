package com.cjc.chatchat.service.impl;

import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import com.cjc.chatchat.exception.UserAlreadyLoginException;
import com.cjc.chatchat.exception.UserLoginAcctOrUserPswdIncorrectException;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserPOMapper userPOMapper;

    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {

        logger.info("loginAcct:"+loginAcct);

        UserPOExample userPOExample = new UserPOExample();

        UserPOExample.Criteria criteria = userPOExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<UserPO> userPOList = userPOMapper.selectByExample(userPOExample);

        /**
         * 如果为空
         */
        if(userPOList==null || userPOList.size()==0){
            throw new UserLoginAcctOrUserPswdIncorrectException(ChatChatConstant.MESSAGE_LOGIN_FAILED);
        }

        // 判断该用户是否已经登录
        UserPO userPO = userPOList.get(0);
        Integer isLogin = userPO.getIsLogin();

        if(isLogin.equals(ChatChatConstant.ATTR_IS_LOGIN)){
            // 如果已经登录,抛出异常
            throw new UserAlreadyLoginException(ChatChatConstant.MESSAGE_USER_ALREADY_LOGIN);

        }

        return new SecurityUser(userPO);
    }
}
