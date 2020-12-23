package com.cjc.chatchat.ws;

import com.cjc.chatchat.entity.UserVO;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/11
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 **/
public class MultiChatEndpointUserMapper {

    private MultiChatEndpoint chatEndpoint;
    private UserVO userVO;
    private boolean isCurrentLogin;

    public MultiChatEndpointUserMapper(MultiChatEndpoint chatEndpoint, UserVO userVO) {
        this.chatEndpoint = chatEndpoint;
        this.userVO = userVO;
    }

    public MultiChatEndpointUserMapper() {
    }



    public MultiChatEndpoint getChatEndpoint() {
        return chatEndpoint;
    }

    public void setChatEndpoint(MultiChatEndpoint chatEndpoint) {
        this.chatEndpoint = chatEndpoint;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }




    @Override
    public boolean equals(Object obj) {

        MultiChatEndpointUserMapper otherMapper = (MultiChatEndpointUserMapper) (obj);
        String thisLoginAcct = this.getUserVO().getLoginAcct();
        String otherLoginAcct = otherMapper.getUserVO().getLoginAcct();
        if(thisLoginAcct.equals(otherLoginAcct)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "MultiChatEndpointUserMapper{" +
                "chatEndpoint=" + chatEndpoint +
                ", userVO=" + userVO +
                '}';
    }
}
