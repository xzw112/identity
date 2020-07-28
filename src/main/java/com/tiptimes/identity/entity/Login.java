package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {

    private String username;

    private String password;
}
