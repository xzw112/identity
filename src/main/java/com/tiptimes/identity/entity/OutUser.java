package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Data
public class OutUser implements Serializable {
    @Id
    private String id;

    private String loginName;

    private String loginPassword;

    private String userName;

    private String nickName;

    private String headerUrl;

    private Integer sex;

    private String ipAddr;

    private Integer status;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private Integer userOutType; //外部用户类型（1普通 2学生）--外部用户专用

}