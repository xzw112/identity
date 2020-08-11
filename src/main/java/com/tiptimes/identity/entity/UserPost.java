package com.tiptimes.identity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPost implements Serializable {
    @ApiModelProperty("用户岗位id")
    private Integer id;
    @ApiModelProperty("岗位id")
    private Integer postId;
    @ApiModelProperty("用户id")
    private String userId;

}