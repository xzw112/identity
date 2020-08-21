package com.tiptimes.identity.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Information {
    private String id;

    private String title;

    private Integer sendType;

    private Integer informationType;

    private Integer isRelease;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer isDel;

    private String content;


}