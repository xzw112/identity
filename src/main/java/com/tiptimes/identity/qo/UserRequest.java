package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    private Integer userType;

    private String userId;
}
