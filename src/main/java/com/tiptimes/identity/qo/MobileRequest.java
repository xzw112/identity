package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MobileRequest implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String phoneNumber; // 手机号

    @ApiModelProperty(value = "验证码")
    private String code; // 验证码
}
