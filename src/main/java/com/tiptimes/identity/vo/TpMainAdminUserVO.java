package com.tiptimes.identity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TpMainAdminUserVO {

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

    private String updateTimeStr;//更新时间

    private Integer isLeave; // 是否离职(0未离职 1已离职)

    private Integer nature; // 人员性质（0正式编 1社会化）

    private String post; // 岗位

    private Integer order; // 排序

    private String department; // 部门

    private String headerUrl; // 头像

}