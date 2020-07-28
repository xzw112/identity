package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignOutRequest implements Serializable {

    private String token;

    private String userId;
}
