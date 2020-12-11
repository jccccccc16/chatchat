package com.cjc.chatchat.service.impl;

import com.cjc.chatchat.entity.SecurityUser;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import com.cjc.chatchat.mapper.UserPOMapper;
import com.cjc.chatchat.service.api.UserService;
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

    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {

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

        return new SecurityUser(userPO);
    }
}
