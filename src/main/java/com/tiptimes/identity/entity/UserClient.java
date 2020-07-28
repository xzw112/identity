package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserClient implements Serializable {

    private Integer id;

    private String clientId;

    private String userId;

}