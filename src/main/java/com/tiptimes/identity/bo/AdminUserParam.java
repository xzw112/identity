package com.tiptimes.identity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdminUserParam extends BaseParam{

    @ApiModelProperty(value = "原始登录密码")
    private String loginPassword;//原始登录密码

    @ApiModelProperty(value = "新密码")
    private String newPassword;//新密码

    @ApiModelProperty(value = "用户id")
    private String id;//用户id

    @ApiModelProperty(value = "单位id")
    private String departmentId;//单位id

    @ApiModelProperty(value = "用户类型 1内部用户 2外部用户")
    private Integer userType; // 用户类型

    @ApiModelProperty(value = "是否离职 0未离职 1已离职")
    private Integer isLeave; // 是否离职
}
