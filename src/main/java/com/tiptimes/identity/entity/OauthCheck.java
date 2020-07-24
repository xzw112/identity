package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OauthCheck implements Serializable {

    private String[] aud;

    private String exp;

    private String user_name;

    private String jti;

    private String client_id;

    private String[] scope;
}
