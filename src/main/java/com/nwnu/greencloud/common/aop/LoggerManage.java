package com.nwnu.greencloud.common.aop;

import java.lang.annotation.*;

/**
* @Author zhangqi
* @Desccription: 日志注解
* @Date: 11:54 2018/5/5
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {
    public String description();
}
