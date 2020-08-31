package com.tiptimes.identity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Opinion {

    private String id;
    @ApiModelProperty("建议类型")
    private Integer opinionType;

    @ApiModelProperty("建议标题")
    private String opinionTitle;

    @ApiModelProperty("建议内容")
    private String opinionContent;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("附件id")
    private String fileId;

    @ApiModelProperty("附件名称")
    private String fileName;

}