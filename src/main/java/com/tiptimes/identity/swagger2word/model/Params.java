package com.tiptimes.identity.swagger2word.model;

import lombok.Data;

/**
 * @author davidliu
 * @className Params
 * @description 参数说明
 * @date 2020/8/10 17:35
 */
@Data
public class Params {

    /**
     * 参数名
     */
    private String name;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 说明
     */
    private String remark;
}
