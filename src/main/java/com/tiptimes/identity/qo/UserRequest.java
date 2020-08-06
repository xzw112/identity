package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    // 内部用户用
    private Integer userType;

    private String userId;

    private Integer isLeave;
}
