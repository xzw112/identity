package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求tokens所需参数
 */
@Data
public class OauthRequest implements Serializable {

    //clientId
    private String client_id = "c2";
    //clientSecret
    private String client_secret = "secret";
    //用户名
    private String username = "admin";
    //密码
    private String password = "admin";
}
