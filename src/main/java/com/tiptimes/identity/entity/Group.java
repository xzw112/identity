package com.tiptimes.identity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Group {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("父分组id")
    private Integer parentId;

    @ApiModelProperty("分组名称")
    private String groupName;

    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("是否删除（0未删除、1已删除）")
    private Integer isDel;

    @ApiModelProperty("状态（0启用、1禁用）")
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    @ApiModelProperty("部门id")
    private Integer departmentId;

}