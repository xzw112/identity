package com.tiptimes.identity.annotation;

import java.lang.annotation.*;

/**
 * ClassName CustomLog
 * Description: 自定义日志注解
 * Author user
 * Date 2020/3/26
 * Version V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 操作详情
     */
    String operateDetail();

    /**
     * 操作类型
     */
    String operateType();

    /**
     * 模块名称
     */
    String moduleName();
}
