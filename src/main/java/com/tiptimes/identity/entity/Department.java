package com.tiptimes.identity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Department {
    @Id
    private Integer id;

    @ApiModelProperty(value = "部门父节点")
    private Integer parentId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门主管")
    private String header;

    @ApiModelProperty(value = "部门分管领导人")
    private String reduceHeader;

    @ApiModelProperty(value = "是否删除(0未删除、1已删除)")
    private Integer isDel;

    @ApiModelProperty(value = "状态 (0启用、1禁用)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "排序")
    private String sort;

}