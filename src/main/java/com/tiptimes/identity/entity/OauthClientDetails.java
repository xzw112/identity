package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class OauthClientDetails implements Serializable {
    @Id
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private Date createTime;

    private Byte archived;

    private Byte trusted;

    private String autoapprove;

    private String additionalInformation;

    private String redirectUrl; // 跳转至客户端地址

    private String imgUrl; // 图标

    private String status; //是否启用

    private String clientType; //客户端类型

    private String clientName; // 客户端名称

    private String clientDomain; // 应用领域（1内部应用 2外部应用）

}