package com.tiptimes.identity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSystemLogVo implements Serializable {

    private String id;

    private Integer operateType;

    private String operateLog;

    private String userId;

    private String userName;

    private String loginName;

    private String createTime;

    private String ipAddress;
}
