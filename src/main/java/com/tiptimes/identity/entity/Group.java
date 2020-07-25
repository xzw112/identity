package com.tiptimes.identity.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Group {

    @Id
    private Integer id;

    private Integer parentId;

    private String groupName;

    private Integer order;

    private Integer isDel;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

}