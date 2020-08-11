package com.tiptimes.identity.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class UserGroup {

    @Id
    @ApiModelProperty("用户分组记录表id")
    private Integer id;

    @ApiModelProperty("分组id")
    private Integer groupId;
    @ApiModelProperty("用户id")
    private String userId;

}