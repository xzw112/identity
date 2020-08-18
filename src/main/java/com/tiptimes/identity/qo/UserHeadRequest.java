package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserHeadRequest implements Serializable {

    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户头像")
    private String headerUrl;
}
