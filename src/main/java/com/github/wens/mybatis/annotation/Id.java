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
public @interface Id {

    /**
     * 字段值（驼峰命名方式，该值可无）
     */
    String value() default "";

    /**
     * 主键ID，默认 ID 自增
     * <p/>
     * {@link IdType}
     */
    IdType type() default IdType.ID_WORKER;
}
