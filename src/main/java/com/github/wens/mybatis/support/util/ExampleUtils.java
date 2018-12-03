package com.github.wens.mybatis.support.util;


import com.github.wens.mybatis.example.Example;
import com.github.wens.mybatis.exception.MybatisQuickException;
import com.github.wens.mybatis.support.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class ExampleUtils {

    public static void orderBy(Example<?> example , String field ){
        String[] items = field.split(":");
        Example.OrderBy orderBy = example.orderBy(items[0]);

        if(items.length == 2 ){
            if("asc".equals(items[1])){
                orderBy.asc();
            }else if("desc".equals(items[1])){
                orderBy.desc();
            }else{
                orderBy.desc();
            }
        }else{
            orderBy.desc() ;
        }
    }


    public static void fillCondition(Example.Criteria criteria, Object condition) {

        Field[] fields = condition.getClass().getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {

            if (field.getAnnotation(Ignore.class) != null) {
                continue;
            }

            String name = field.getName();
            Object value = getValue(condition, field);
            if (value == null) {
                continue;
            }

            Annotation[] annotations = field.getDeclaredAnnotations();

            if (annotations == null || annotations.length == 0) {
                criteria.andLike(name, "%" + value + "%");
            } else {
                Annotation annotation = annotations[0];

                if (annotation instanceof Equal) {
                    Equal equal = (Equal) annotation;
                    criteria.andEqualTo(StringUtils.isEmpty(equal.name()) ? name : equal.name(), value);
                } else if (annotation instanceof LeftLike) {
                    LeftLike leftLike = (LeftLike) annotation;
                    criteria.andLike(StringUtils.isEmpty(leftLike.name()) ? name : leftLike.name(), value + "%");
                } else if (annotation instanceof RightLike) {
                    RightLike rightLike = (RightLike) annotation;
                    criteria.andLike(StringUtils.isEmpty(rightLike.name()) ? name : rightLike.name(), "%" + value);
                } else if (annotation instanceof FullLike) {
                    FullLike fullLike = (FullLike) annotation;
                    criteria.andLike(StringUtils.isEmpty(fullLike.name()) ? name : fullLike.name(), "%" + value + "%");
                } else if (annotation instanceof In) {
                    In in = (In) annotation;
                    if (!field.getType().isArray() || !field.getType().isAssignableFrom(Collection.class)) {
                        throw new MybatisQuickException("Field type must be array or collection for annotation In.");
                    }
                    criteria.andIn(StringUtils.isEmpty(in.name()) ? name : in.name(), field.getType().isArray() ? Arrays.asList((Object[]) value) : (Collection<Object>) value);
                } else if (annotation instanceof LessThan) {
                    LessThan lessThan = (LessThan) annotation;
                    criteria.andLessThan(StringUtils.isEmpty(lessThan.name()) ? name : lessThan.name(), value);
                } else if (annotation instanceof GreatThan) {
                    GreatThan greatThan = (GreatThan) annotation;
                    criteria.andGreaterThan(StringUtils.isEmpty(greatThan.name()) ? name : greatThan.name(), value);
                } else if (annotation instanceof GreatOrEqualThan) {
                    GreatOrEqualThan greatOrEqualThan = (GreatOrEqualThan) annotation;
                    criteria.andGreaterThanOrEqualTo(StringUtils.isEmpty(greatOrEqualThan.name()) ? name : greatOrEqualThan.name(), value);
                } else if (annotation instanceof LessOrEqualThan) {
                    LessOrEqualThan lessOrEqualThan = (LessOrEqualThan) annotation;
                    criteria.andLessThanOrEqualTo(StringUtils.isEmpty(lessOrEqualThan.name()) ? name : lessOrEqualThan.name(), value);
                } else {
                    criteria.andLike(name, "%" + value + "%");
                }
            }
        }
    }

    private static Object getValue(Object obj, Field field) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
