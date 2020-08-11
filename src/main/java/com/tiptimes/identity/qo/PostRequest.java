package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostRequest extends BaseReauest implements Serializable {
    // 岗位名称
    @ApiModelProperty("岗位名称")
    private String postName;
    @ApiModelProperty("岗位id")
    private Integer postId;
    @ApiModelProperty("部门id")
    private Integer departmentId;
}
