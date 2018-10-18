package com.github.wens.mybatis;

import com.github.wens.mybatis.annotation.IdType;
import com.github.wens.mybatis.idworker.IdWorker;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.util.*;

/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class MybatisDefaultParameterHandler extends DefaultParameterHandler {

    public MybatisDefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        super(mappedStatement, processParameterObject(mappedStatement, parameterObject), boundSql);
    }


    protected static Object processParameterObject(MappedStatement ms, Object parameterObject) {
        if(ms.getSqlCommandType() != SqlCommandType.INSERT){
            return parameterObject ;
        }

        Collection<Object> parameters = getCollectionFromParameters(parameterObject);
        if (parameters != null) {
            List<Object> list = new ArrayList<>(parameters.size());
            for (Object parameter : parameters) {
                list.add(trySetKeyValue(ms, parameter));
            }
            return list;
        } else {
            return trySetKeyValue(ms, parameterObject);
        }
    }


    protected static Collection<Object> getCollectionFromParameters(Object parameter) {
        Collection<Object> parameters = null;
        if (parameter instanceof Collection) {
            parameters = (Collection) parameter;
        } else if (parameter instanceof Map) {
            Map parameterMap = (Map) parameter;
            if (parameterMap.containsKey("collection")) {
                parameters = (Collection) parameterMap.get("collection");
            } else if (parameterMap.containsKey("list")) {
                parameters = (List) parameterMap.get("list");
            } else if (parameterMap.containsKey("array")) {
                parameters = Arrays.asList((Object[]) parameterMap.get("array"));
            }
        }
        return parameters;
    }


    protected static Object trySetKeyValue(MappedStatement ms, Object parameterObject) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(parameterObject.getClass());
        if (tableInfo != null && tableInfo.getIdType() == IdType.ID_WORKER) {
            MetaObject metaParam = ms.getConfiguration().newMetaObject(parameterObject);
            Object value = metaParam.getValue(tableInfo.getKeyProperty());
            if (value == null) {
                Class<?> idType = metaParam.getGetterType(tableInfo.getKeyProperty());
                if (idType.isAssignableFrom(String.class)) {
                    value = String.valueOf(IdWorker.getId());
                } else {
                    value = IdWorker.getId();
                }
                metaParam.setValue(tableInfo.getKeyProperty(), value);
            }
            return metaParam.getOriginalObject();
        }else{
            return parameterObject;
        }
    }

}
