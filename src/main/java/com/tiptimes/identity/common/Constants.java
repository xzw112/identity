package com.tiptimes.identity.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * 常量类
 */
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
    public static final String WEB_SERVER_REDIRECT_URI = "http://localhost:" + PORT + "/toIndex";
    // 授权类型
    public static final String AUTHORIZED_GRANT_TYPES = "client_credentials,password,authorization_code,implicit,refresh_token";
    // 客户端秘钥
    public static final String CLIENT_SECRET = "secret";
    // 客户端跳转地址
    public static final String REDIRECT_URI = "http://localhost:8081/toIndex";
    // jwt 秘钥
    public static final String SIGNING_KEY = "xzw";
    // token 有效时间
    public static final Integer ACCESS_TOKEN_VALIDITY = 7200;
    // refresh_token 有效时间
    public static final Integer REFRESH_TOKEN_VALIDITY = 2592000;
    // oauth2 认证配置 =================



}
