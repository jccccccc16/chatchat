package com.cjc.chatchat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/5
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 **/
@Component
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {

    private String host;
    private String path;
    private String method;
    private String appcode;
    private String tpl_id;

    public ShortMessageProperties(){

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }


    public String getTpl_id() {
        return tpl_id;
    }

    public void setTpl_id(String tpl_id) {
        this.tpl_id = tpl_id;
    }
}
