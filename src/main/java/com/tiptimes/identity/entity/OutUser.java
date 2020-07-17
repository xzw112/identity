package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;


@Data
public class OutUser {
    @Id
    private String id;

    private String loginName;

    private String userName;

    private String headerUrl;

    private Integer sex;

    private String registerIp;

    private Integer status;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

}