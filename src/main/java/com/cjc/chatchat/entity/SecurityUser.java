package com.cjc.chatchat.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 **/
public class SecurityUser extends User {

    private UserPO originalUser;

    public SecurityUser(UserPO userPO) {
        super(userPO.getLoginAcct(), userPO.getUserPswd(), new ArrayList<GrantedAuthority>());
    }


    public UserPO getOriginalUser() {
        return originalUser;
    }

    public void setOriginalUser(UserPO originalUser) {
        this.originalUser = originalUser;
    }
}
