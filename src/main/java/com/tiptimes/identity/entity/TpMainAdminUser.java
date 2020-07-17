package com.tiptimes.identity.entity;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class TpMainAdminUser {

    @Id
    private String id;

    private String userName;

    private String loginName;

    private String loginPassword;

    private String userContact;

    private String userAddress;

    private String mail;

    private String roleId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private Integer isAdmin;

    private Integer sort;

}