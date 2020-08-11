package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReauest implements Serializable {

    @ApiModelProperty("页码")
    private int pageNumber = 1;
    @ApiModelProperty("页面大小默认Integer最大值")
    private int pageSize = Integer.MAX_VALUE;
}
