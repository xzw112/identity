package com.tiptimes.identity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Post implements Serializable {

    @ApiModelProperty("岗位id")
    private Integer id;
    @ApiModelProperty("岗位名称")
    private String postName;
    @ApiModelProperty("岗位编码")
    private String postCode;
    @ApiModelProperty("岗位描述")
    private String postDec;
    @ApiModelProperty("岗位状态（0启用、1禁用）")
    private Integer status;
    @ApiModelProperty("岗位排序")
    private String sort;
    @ApiModelProperty("是否删除（0未删除、1已删除）")
    private Integer isDel;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("部门id")
    private Integer departmentId;

}