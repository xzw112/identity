package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterOutUserRequest implements Serializable {

    private String id;
    @ApiModelProperty("登录账号")
    private String loginName;
    @ApiModelProperty("手机号")
    private String userContact;
    @ApiModelProperty("登录密码 密码需要使用Base64().encode()加密")
    private String loginPassword;
    @ApiModelProperty("重复密码 密码需要使用Base64().encode()加密")
    private String reportLoginPassword;
    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("性别(1男 0女)")
    private Integer sex;
    @ApiModelProperty("出生日期")
    private String birth;
}
