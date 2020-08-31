package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClientTopRequest implements Serializable {

    @ApiModelProperty("是否置顶 1是 0否")
    private Integer isTop = 0;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("id")
    private Integer id;
}
