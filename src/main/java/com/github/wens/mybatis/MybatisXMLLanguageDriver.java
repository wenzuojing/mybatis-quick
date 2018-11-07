package com.github.wens.mybatis;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class MybatisXMLLanguageDriver extends XMLLanguageDriver {

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new MybatisDefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}
