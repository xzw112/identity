package com.tiptimes.identity.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 常量类
 */
@Component
public class Constants {
    @Value("${server.port}")
    private static String PORT;

    //前台展示虚拟密码
    public static final String VIRTUAL_PASSWORD = "#*virtual@$password*";

    // oauth2 认证配置 =================
    // 客户端Id
    public static final String CLIENT_ID = "c1";
    // 客户端资源
    public static final String RESOURCE_ID = "res1";
    // 权限标识
    public static final String SCOPE = "ALL";
    // 服务端跳转地址
    public static final String WEB_SERVER_REDIRECT_URI = "http://192.168.1.71:" + PORT + "/toIndex";
    // 授权类型
    public static final String AUTHORIZED_GRANT_TYPES = "client_credentials,password,authorization_code,implicit,refresh_token";
    // 客户端秘钥
    public static final String CLIENT_SECRET = "secret";
    // 客户端跳转地址
    public static final String REDIRECT_URI = "http://192.168.1.71:8081/toIndex";
    // jwt 秘钥
    public static final String SIGNING_KEY = "xzw";
    // token 有效时间
    public static final Integer ACCESS_TOKEN_VALIDITY = -1; // -1设置为永不过期
    // refresh_token 有效时间
    public static final Integer REFRESH_TOKEN_VALIDITY = -1; // -1设置为永不过期
    // oauth2 认证配置 =================

    // token 存储配置
    public static final TimeUnit UNIT = TimeUnit.DAYS;
    public static final Integer TIME_COUNT = 2;

    // ======客户端退出开始======
    public static final List<String> CLIENTLIST = new ArrayList<>();
    // 1.知识学习平台
    //private static final String ZS_LOGOUT_URL = "http://192.168.1.120:8083/identityLogout?token=";
    private static final String ZS_LOGOUT_URL = "http://study2.tiptimes.com/identityLogout?token=";
    Constants(){
        CLIENTLIST.add(ZS_LOGOUT_URL);
    }
    // ======客户端退出结束======


    // ==========微信登录--基础数据===========
    // 1.微信开放平台 appid
    public static final String APP_ID = "wx5ca958845d4af7d1";
    // 2.微信开放平台 appsecret
    public static final String APP_SECRET = "1d70d2590be19db30f4f6a2020ebf381";
    // 回调地址
    public static final String WX_REDIRECT_URL = "http://tbrz.tiptimes.com/customer/weChatCallBack/callback";
    // ==========微信登录--基础数据===========

}
