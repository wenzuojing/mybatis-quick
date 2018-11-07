package com.github.wens.mybatis;

import com.github.wens.mybatis.annotation.Field;
import com.github.wens.mybatis.annotation.Id;
import com.github.wens.mybatis.annotation.Table;
import com.google.common.base.CaseFormat;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class TableInfoHelper {

    private static Map<String, TableInfo> tableInfoCache = new ConcurrentHashMap<String, TableInfo>();


    public synchronized static TableInfo getTableInfo(Class<?> clazz) {
        TableInfo ti = tableInfoCache.get(clazz.getName());
        if (ti != null) {
            return ti;
        }
        List<java.lang.reflect.Field> list = getAllFields(clazz);
        TableInfo tableInfo = new TableInfo();

		/* 表名 */
        Table table = clazz.getAnnotation(Table.class);
        if (table != null && table.value() != null && table.value().trim().length() > 0) {
            tableInfo.setTableName(table.value());
        } else {
            tableInfo.setTableName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, clazz.getSimpleName()));
        }

        List<TableFieldInfo> fieldList = new ArrayList<TableFieldInfo>();
        for (java.lang.reflect.Field field : list) {
            /* 主键ID */
            Id tableId = field.getAnnotation(Id.class);
            if (tableId != null) {
                tableInfo.setIdType(tableId.type());
                if (tableId.value() != null && !"".equals(tableId.value())) {
                    /* 自定义字段 */
                    tableInfo.setKeyColumn(tableId.value());
                    tableInfo.setKeyRelated(true);
                } else {
                    tableInfo.setKeyColumn(field.getName());
                }
                tableInfo.setKeyProperty(field.getName());
                continue;
            }

			/* 获取注解属性，自定义字段 */
            Field tableField = field.getAnnotation(Field.class);
            if (tableField != null && tableField.value() != null && !"".equals(tableField.value())) {
                fieldList.add(new TableFieldInfo(true, tableField.value(), field.getName()));
                continue;
            }

			/* 字段 */
            fieldList.add(new TableFieldInfo(field.getName()));
        }

		/* 字段列表 */
        tableInfo.setFieldList(fieldList);
        tableInfoCache.put(clazz.getName(), tableInfo);
        return tableInfo;
    }


    private static List<java.lang.reflect.Field> getAllFields(Class<?> clazz) {
        List<java.lang.reflect.Field> result = new LinkedList<java.lang.reflect.Field>();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {

			/* 过滤 transient关键字修饰则该属性 */
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }

			/* 过滤注解非表字段属性 */
            Field tableField = field.getAnnotation(Field.class);
            if (tableField == null || tableField.exist()) {
                result.add(field);
            }
        }

		/* 处理父类字段 */
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.equals(Object.class)) {
            return result;
        }
        result.addAll(getAllFields(superClass));
        return result;
    }

}
