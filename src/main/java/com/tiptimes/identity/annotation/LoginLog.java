package com.tiptimes.identity.annotation;


import java.lang.annotation.*;

/**
 * 系统登录登出日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLog {

    // 操作日志
    String operateLog();

    // 操作类型
    int operateType();
}
