package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SignOutRequest implements Serializable {

    @ApiModelProperty("登录用户token")
    private String token;

    @ApiModelProperty("登录用户id")
    private String userId;
}
