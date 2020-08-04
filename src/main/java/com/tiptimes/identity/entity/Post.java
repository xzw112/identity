package com.tiptimes.identity.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Post implements Serializable {

    private Integer id;

    private String postName;

    private String postCode;

    private String postDec;

    private Integer status;

    private String sort;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private Integer departmentId;

}