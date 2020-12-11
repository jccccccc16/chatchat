package com.cjc.chatchat.config;

import com.cjc.chatchat.util.ChatUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 0:35
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class Md5PasswordEncoded implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        // 1. 获取源的字符串
        String password = charSequence.toString();

        // 2.加密
        String encodedPassword = ChatUtil.md5(password);

        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        String result = ChatUtil.md5(rawPassword.toString());
        return Objects.equals(result,encodedPassword);

    }


}
