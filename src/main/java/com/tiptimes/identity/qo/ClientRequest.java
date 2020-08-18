package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientRequest extends BaseReauest implements Serializable {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("查询参数")
    private String searchText;

    @ApiModelProperty("登录类型：1.内部登录 2外部登录")
    private Integer loginType = 2; // 1.内部登录 2外部登录
}
