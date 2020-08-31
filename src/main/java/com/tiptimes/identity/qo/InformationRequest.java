package com.tiptimes.identity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InformationRequest extends BaseReauest implements Serializable {

    @ApiModelProperty("标题")
    private String title = ""; // 标题
    @ApiModelProperty("消息类型:1通知 2公告 3系统消息 0全部")
    private Integer  informationType = 0; // 消息类型
    @ApiModelProperty("发送对象：1 内部用户 2外部用户")
    private Integer sendType = 0; // 发送对象
    @ApiModelProperty("是否删除：1未删除 0删除")
    private Integer isDel = 1;
}
