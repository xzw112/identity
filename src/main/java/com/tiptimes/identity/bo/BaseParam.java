package com.tiptimes.identity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseParam {

    @ApiModelProperty(value = "页码")
    private Integer pageNumber;
    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

}
