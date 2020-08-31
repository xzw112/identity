package com.tiptimes.identity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TpMainAdminUserVO implements Serializable {

    private String id;

    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "登录密码")
    private String loginPassword;
    @ApiModelProperty(value = "联系方式")
    private String userContact;
    @ApiModelProperty(value = "用户名")
    private String userAddress;
    @ApiModelProperty(value = "email")
    private String mail;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "是否是超级管理员")
    private Integer isAdmin;
    @ApiModelProperty(value = "更新时间")
    private String updateTimeStr;//更新时间
    @ApiModelProperty(value = "是否离职(0未离职 1已离职)")
    private Integer isLeave; // 是否离职(0未离职 1已离职)
    @ApiModelProperty(value = "人员性质（0正式编 1社会化）")
    private Integer nature; // 人员性质（0正式编 1社会化）
    @ApiModelProperty(value = "岗位")
    private String post; // 岗位
    @ApiModelProperty(value = "岗位名称")
    private String postName; // 岗位名称
    @ApiModelProperty(value = "副岗")
    private String deputyPostId;  // 副岗
    @ApiModelProperty(value = "副岗名称")
    private String deputyPostName; // 副岗名称
    @ApiModelProperty(value = "排序")
    private Integer sort; // 排序
    @ApiModelProperty(value = "部门id")
    private String departmentId; // 部门
    @ApiModelProperty(value = "部门名称")
    private String departmentName; // 部门名称
    @ApiModelProperty(value = "头像")
    private String headerUrl; // 头像
    @ApiModelProperty(value = "所属分组--id")
    private String groupId;
    @ApiModelProperty(value = "所属分组--名称")
    private String groupName;

}