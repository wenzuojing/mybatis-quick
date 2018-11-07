package com.github.wens.mybatis.support.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BeanUtils {

    public JSONObject toJSONObject(Object object) {
        return JSON.parseObject(JSON.toJSONString(object));
    }

    public JSONArray toJSONArray(Object object) {
        return JSON.parseArray(JSON.toJSONString(object));
    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProperty(Object bean, String name) {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <K, V> Map<K, V> toMap(List<V> list, String keyName) {
        return toMap(list, item -> (K) getProperty(item, keyName));
    }

    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> function) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }
        return Maps.uniqueIndex(list, function);
    }

    public static <V, L> List<V> toList(List<L> list, String keyName) {
        return toList(list, item -> (V) getProperty(item, keyName));
    }

    public static <V, L> List<V> toList(List<L> list, Function<L, V> function) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        return Lists.transform(list, function);
    }


    public static <K, V> Map<K, Collection<V>> toGroup(List<V> list, String keyName) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }
        return toGroup(list, item -> (K) getProperty(item, keyName));
    }


    public static <K, V> Map<K, Collection<V>> toGroup(List<V> list, Function<V, K> function) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }
        return Multimaps.index(list, function).asMap();
    }


}
