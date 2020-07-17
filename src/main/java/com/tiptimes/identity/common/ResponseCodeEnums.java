package com.tiptimes.identity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* @Description:    返回状态码
* @Author:         tsw
* @CreateDate:     2019/9/10 10:57
* @Version:        1.0
*/
@AllArgsConstructor
@Getter
public enum ResponseCodeEnums {
    FAILURE(0),//失败
    SUCCESS(1);//成功
    private Integer code;
}
