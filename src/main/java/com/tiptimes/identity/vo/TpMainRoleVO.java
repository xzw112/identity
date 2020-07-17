package com.tiptimes.identity.vo;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class TpMainRoleVO {

    @Id
    private String id;

    private String roleName;

    private String roleNumber;

    private String orderNumber;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private String updateTimeStr;
}