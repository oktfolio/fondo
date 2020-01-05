package com.oktfolio.fondo.common.annotation;

import com.oktfolio.fondo.common.constant.LogTypeEnum;

import java.lang.annotation.*;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 日志名称
     * @return
     */
    String name() default "";

    /**
     * 日志类型
     * @return
     */
    LogTypeEnum type() default LogTypeEnum.OPERATION;
}
