package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InformationRequest extends BaseReauest implements Serializable {

    private String title = ""; // 标题

    private Integer  informationType = 0; // 消息类型

    private Integer sendType = 0; // 发送对象

    private Integer isDel = 1;
}
