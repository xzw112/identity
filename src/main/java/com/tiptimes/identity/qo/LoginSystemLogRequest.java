package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSystemLogRequest extends BaseReauest implements Serializable {

    private Integer operateType; // 操作类型

    private String startTime;

    private String endTime;

    private String searchText; // 检索字段: ip 操作人 账号
}
