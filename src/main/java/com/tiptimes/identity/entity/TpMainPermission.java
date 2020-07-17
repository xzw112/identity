package com.tiptimes.identity.entity;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class TpMainPermission {

    @Id
    private Integer id;

    private String permissionName;

    private String permissionUrl;

    private String parentId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer isDelete;

    private String orderNumber;

    private String remark;

}