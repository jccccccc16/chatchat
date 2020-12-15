package com.cjc.chatchat.controller;

import com.cjc.chatchat.config.OSSProperties;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserRegisterVO;
import com.cjc.chatchat.service.api.UserService;
import com.cjc.chatchat.util.ChatUtil;
import com.cjc.chatchat.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/9
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private OSSProperties ossProperties;



    private Logger logger = LoggerFactory.getLogger(UserController.class);



//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public String register(UserRegisterVO userRegisterVO, ModelMap modelMap, HttpServletRequest request){
//
//        // 如果userpo为空
//        if(userRegisterVO==null){
//            modelMap.addAttribute(ChatChatConstant.ATTR_MESSAGE, ChatChatConstant.MESSAGE_REGISTER_DATA_NULL);
//            return "register";
//        }
//
//        // 判断两次输入的密码是否一致
//        if(!userRegisterVO.getUserPswd().equals(userRegisterVO.getRepeatUserPswd())){
//            // 如果不一致
//            modelMap.addAttribute(ChatChatConstant.ATTR_MESSAGE,ChatChatConstant.MESSAGE_PASSWORD_NOT_SAME);
//            return "register";
//        }
//
//        String passwordEncoded = ChatUtil.md5(userRegisterVO.getUserPswd());
//
//        userRegisterVO.setUserPswd(passwordEncoded);
//
//
//
//        try{
//            userService.saveUser(userPO);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            modelMap.addAttribute(ChatChatConstant.ATTR_MESSAGE,ChatChatConstant.MESSAGE_SAVE_USER_FAILED);
//            logger.warn("注册失败");
//            return "register";
//        }
//
//        logger.info("注册成功跳转到登录页面");
//
//        String url="/login.html";
//
//        String fullUrl = ChatUtil.getPath(request,url);
//
//        return "redirect:"+fullUrl;
//    }
//

    @RequestMapping("/user/register.json")
    @ResponseBody
    public ResultEntity<String> register(@RequestBody UserRegisterVO userRegisterVO){

        // userRegisterVO
        if(userRegisterVO==null){
            return ResultEntity.failed(ChatChatConstant.MESSAGE_REGISTER_DATA_NULL);
        }

        // 判断两次输入的密码是否一致
        if(!userRegisterVO.getUserPswd().equals(userRegisterVO.getRepeatUserPswd())){

            // 如果不一致
            return ResultEntity.failed(ChatChatConstant.MESSAGE_PASSWORD_NOT_SAME);
        }

        // 如果没有错误信息，保存用户信息
        // 将密码进行加密处理
        String passwordEncoded = ChatUtil.md5(userRegisterVO.getUserPswd());
        userRegisterVO.setUserPswd(passwordEncoded);

        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userRegisterVO,userPO);

        try{

            userService.saveUser(userPO);
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            logger.warn("注册失败");
            return ResultEntity.failed(ChatChatConstant.MESSAGE_SAVE_USER_FAILED);
        }
    }


    @RequestMapping("/user/upload/headerPicture.json")
    @ResponseBody
    public ResultEntity<String> uploadHeaderPicture(
            @RequestParam("headerPicture")MultipartFile headerPicture) throws IOException {

        logger.info(headerPicture.getOriginalFilename());
        logger.info("准备上传");
        // 1.执行文件上传
        ResultEntity<String> uploadReturnPicResultEntity = ChatUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());

        logger.info("uploadReturnPicResultEntity: "+uploadReturnPicResultEntity);

        // 2.返回上传的结果
        return uploadReturnPicResultEntity;

    }



//    @RequestMapping("/login")
//    public String login(@RequestParam("loginAcct") String loginAcct,
//                        @RequestParam("userPswd") String userPswd,
//                        ModelMap modelMap,
//                        HttpSession session,
//                        HttpServletRequest request){
//
//        // 根据loginAcct查找用户
//        UserPO userByLoginAcct = userService.getUserByLoginAcct(loginAcct);
//
//        // 没有该用户
//        if(userByLoginAcct==null){
//
//            // 返回登录页面
//            modelMap.addAttribute(ChatChatConstant.ATTR_MESSAGE,
//                    ChatChatConstant.MESSAGE_LOGIN_FAILED);
//
//            return "login";
//        }
//
//        // 密码匹配
//
//        String userPswdEncoded = ChatUtil.md5(userPswd);
//
//        String userPswdOrigin = userByLoginAcct.getUserPswd();
//
//        // 如果不匹配
//        if(!userPswdOrigin.equals(userPswdEncoded)){
//
//            logger.info("密码不正确");
//            // 返回登录页面
//            modelMap.addAttribute(ChatChatConstant.ATTR_MESSAGE,
//                    ChatChatConstant.MESSAGE_LOGIN_FAILED);
//            return "login";
//
//
//        }
//
//        logger.info("登录成功");
//
//        session.setAttribute(ChatChatConstant.ATTR_LOGIN_USER,userByLoginAcct);
//
//        String url="/";
//        String fullPath = ChatUtil.getPath(request, url);
//
//        return "redirect:"+fullPath;
//
//
//    }



//    @RequestMapping("/user/login.json")
//    @ResponseBody
//    public ResultEntity<String> login(@RequestBody UserLoginVO userLoginVO,
//                                      HttpSession session){
//
//        String loginAcct = userLoginVO.getLoginAcct();
//        String userPswd = userLoginVO.getUserPswd();
//
//        // 根据loginAcct查找用户
//        UserPO userByLoginAcct = userService.getUserByLoginAcct(loginAcct);
//
//        // 没有该用户
//        if(userByLoginAcct==null){
//            return ResultEntity.failed(ChatChatConstant.MESSAGE_LOGIN_FAILED);
//        }
//
//        // 密码校验
//        // 对明文进行加密
//        String userPswdEncoded = ChatUtil.md5(userPswd);
//
//        String userPswdOrigin = userByLoginAcct.getUserPswd();
//
//        // 如果不匹配
//        if(!userPswdOrigin.equals(userPswdEncoded)){
//
//            return ResultEntity.failed(ChatChatConstant.MESSAGE_LOGIN_FAILED);
//        }
//
//        logger.info("登录成功");
//
//        session.setAttribute(ChatChatConstant.ATTR_LOGIN_USER,userByLoginAcct);
//
//        return ResultEntity.successWithoutData();
//
//    }









}
