package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class OauthClientDetails {
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

}