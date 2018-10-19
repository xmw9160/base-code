package com.xmw.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:15
 * @since V1.0
 */
@Documented
@Target(ElementType.FIELD)  // 作用于字段
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {
    String value();
}
