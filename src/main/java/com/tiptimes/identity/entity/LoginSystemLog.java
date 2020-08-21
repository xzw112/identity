package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginSystemLog implements Serializable {

    private String id;

    private Integer operateType;

    private String operateLog;

    private String userId;

    private Date createTime;

    private String ipAddress;

}