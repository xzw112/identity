package com.tiptimes.identity.enums;

import lombok.Getter;

/**
 * ClassName DataStatus
 * Description: 数据状态枚举类
 * Author user
 * Date 2019/11/6
 * Version V1.0
 **/
@Getter
public enum DataStatus {

    NOT_DELETE(0, "未删除"),
    DELETED(1, "已删除"),
    ENABLED(0, "启用"),
    DISABLED(1, "禁用"),
    NO_PUBLISH(0, "未发布"),
    PUBLISH(1, "已发布"),
    NO_AUDIT(0, "未审核"),
    AUDIT_FAIL(1, "审核未通过"),
    AUDIT_PASS(2, "审核通过");

    private Integer code;

    private String description;

    DataStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
