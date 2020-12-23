package com.cjc.chatchat.constant;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/9
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 **/
public class ChatChatConstant {

    public static final String MESSAGE_REGISTER_DATA_NULL = "数据为空，请重新填写";

    public static final String ATTR_MESSAGE = "message";
    public static final String ATTR_LOGIN_USER = "loginUser";

    public static final String MESSAGE_STRING_INVALIDATE = "非法的字符串";
    public static final String MESSAGE_LOGIN_FAILED = "账号或密码错误";
    public static final String MESSAGE_SAVE_USER_FAILED = "注册失败，请重试";
    public static final String MESSAGE_PASSWORD_NOT_SAME = "输入密码不一致";


    public static final String ATTR_LOGIN_ACCT = "loginAcct";
    public static final Integer ATTR_IS_LOGIN = 1;
    public static final String MESSAGE_USER_ALREADY_LOGIN = "该用户已经登录";

    public static final Integer SET_USER_LOGIN = 1;
    public static final Integer SET_USER_LOGOUT = 0;
    public static final String MESSAGE_USER_ENTER_MULTI_ROOM = "用户进入当前聊天室";
}
