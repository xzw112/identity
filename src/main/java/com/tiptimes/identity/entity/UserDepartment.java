package com.tiptimes.identity.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class UserDepartment {

    @Id
    @ApiModelProperty("用户部门记录表id")
    private Integer id;
    @ApiModelProperty("部门id")
    private Integer departmentId;
    @ApiModelProperty("用户id")
    private String userId;

}