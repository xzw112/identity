package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPwdRequest implements Serializable {

    private String id;
    @ApiModelProperty("旧密码 密码需要使用Base64().encode()加密")
    private String oldPassword;
    @ApiModelProperty("新密码 密码需要使用Base64().encode()加密")
    private String newPassword;
    @ApiModelProperty("重复密码 密码需要使用Base64().encode()加密")
    private String reportPassword;
}
