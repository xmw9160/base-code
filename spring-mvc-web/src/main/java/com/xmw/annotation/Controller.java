package com.xmw.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller 控制层注解
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:15
 * @since V1.0
 */
@Documented //Java Doc
@Target(ElementType.TYPE) //作用在类上
@Retention(RetentionPolicy.RUNTIME) // 限制Annotation的生命周期, 运行时保留
public @interface Controller {

    /**
     * 作用于该类上的注解只有一个value属性, 就是controller的名称
     *
     * @author mingwei.xia
     * @date 2018/9/25 10:36
     */
    String value();
}
