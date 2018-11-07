package com.github.wens.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wens
 * @Date 2018-10-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {

    /**
     * 字段值（驼峰命名方式，该值可无）
     */
    String value() default "";

    /**
     * <p>
     * 是否为数据库表字段
     * </p>
     * 默认 true 存在，false 不存在
     */
    boolean exist() default true;
}
