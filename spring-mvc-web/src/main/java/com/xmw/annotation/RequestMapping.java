package com.xmw.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RequestMapping
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:16
 * @since V1.0
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})  // 类和方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value();
}
