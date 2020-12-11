package com.cjc.chatchat.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.cjc.chatchat.constant.ChatChatConstant;
import com.cjc.chatchat.entity.UserPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/10
 * Time: 9:30
 * To change this template use File | Settings | File Templates.
 **/
public class ChatUtil {

    private static Logger logger = LoggerFactory.getLogger(ChatUtil.class);

    public static String md5(String source){

        if(source == null || source.equals("")){
            throw new RuntimeException(ChatChatConstant.MESSAGE_STRING_INVALIDATE);
        }


        try {
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] input = source.getBytes();
            byte[] output = messageDigest.digest(input);

            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 按照16进制转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.warn(e.getMessage()+"  加密出错");
        }

        return null;
    }

    /**
     * 清洗数据，洗去头尾空格
     * @param userPO
     * @return
     */
    public static UserPO userPODataClean(UserPO userPO){

        String loginAcctTrim = userPO.getLoginAcct().trim();
        userPO.setLoginAcct(loginAcctTrim);

        String userPswdTrim = userPO.getUserPswd().trim();
        userPO.setUserPswd(userPswdTrim);

        String nameTrim = userPO.getName().trim();
        userPO.setName(nameTrim);

        String usernameTrim = userPO.getUsername().trim();
        userPO.setUsername(usernameTrim);

        return userPO;

    }

    /**
     * 最终效果 http://localhost:8080+/login.html 方便部署后的重定向
     * @param request
     * @return
     */
    public static String getPath(HttpServletRequest request,String url){

        String path="";

        // 最终效果 http://localhost:8080+/login.html
        String fullUrl="";

        // 获取协议
        String scheme = request.getScheme();

        // 获取服务器名字
        String serverName = request.getServerName();

        // 获取端口号
        int serverPort = request.getServerPort();

        // 获取应用名
        String contextPath = request.getContextPath();

        if(contextPath==null || contextPath.equals("")){
            // http://localhost:8080/应用名
            path = scheme+"://"+serverName+":"+serverPort;
        }else{
            path = scheme+"://"+serverName+":"+serverPort+"/"+contextPath;
        }

        fullUrl = path+url;

        return fullUrl;


    }

    /**
     * 专门负责上传文件到OSS服务器的工具方法
     * @param endpoint			OSS参数
     * @param accessKeyId		OSS参数
     * @param accessKeySecret	OSS参数
     * @param inputStream		要上传的文件的输入流
     * @param bucketName		OSS参数
     * @param bucketDomain		OSS参数
     * @param originalName		要上传的文件的原始文件名
     * @return	包含上传结果以及上传的文件在OSS上的访问路径
     */
    public static ResultEntity<String> uploadFileToOss(
            String endpoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String originalName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在OSS服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用UUID生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");



        // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;

        try {
            // 调用OSS客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if(responseMessage == null) {

                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                // 当前方法返回成功
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();

                // 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();

                // 当前方法返回失败
                return ResultEntity.failed("当前响应状态码="+statusCode+" 错误消息="+errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();

            // 当前方法返回失败
            return ResultEntity.failed(e.getMessage());
        } finally {

            if(ossClient != null) {

                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }

    }


    public static void main(String[] args) {
//        String s = "  cj c 1 3 4";
//        System.out.println(s.trim());
        String s = md5("cjc1316");
        System.out.println(s);
    }

}
