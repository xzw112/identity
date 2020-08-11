package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GroupRequest extends BaseReauest implements Serializable {

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分组id")
    private Integer groupId;

    @ApiModelProperty(value = "部门id")
    private Integer departmentId; // 部门id
}
