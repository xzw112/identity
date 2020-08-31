package com.tiptimes.identity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClientUserVo implements Serializable {

    private String id;

    private String realName;

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

    private Integer sort; // 排序

    private String department; // 部门

    private String headerUrl; // 头像

    private String groupId;

    private Integer userType; // 用户类型（1.内部用户 2外部用户）

    private String nickName;

    private Integer sex;

    private String ipAddr;

    private Integer userOutType; // 外部用户类型（1普通 2学生）--外部用户专用

    private String birth; // 生日

    private String nation;// 民族

    private Integer province;// 省

    private Integer city;// 市

    private Integer county;// 县

    private String postCode;// 邮政编码

    private String education;// 学历

    private String departmentId;
    private String departmentName;

    private String deputyPostId;

    private String deputyPost;
    private String groupName;
}
