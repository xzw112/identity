package com.tiptimes.identity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentTreeVo implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "父节点id")
    private String parent;
    @ApiModelProperty(value = "部门名称")
    private String text;
}
