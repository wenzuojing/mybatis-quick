/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.wens.mybatisplus.plugins;

import com.github.wens.mybatisplus.exceptions.MybatisPlusException;
import com.github.wens.mybatisplus.plugins.pagination.DialectFactory;
import com.github.wens.mybatisplus.plugins.pagination.IDialect;
import com.github.wens.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * 分页拦截器
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {

    /* 方言类型 */
    private String dialectType;

    /* 方言实现类 */
    private String dialectClazz;

    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

			/* 不需要分页的场合 */
            if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
                return invocation.proceed();
            }

			/* 定义数据库方言 */
            IDialect dialect = null;
            if (dialectType != null && !"".equals(dialectType)) {
                dialect = DialectFactory.getDialectByDbtype(dialectType);
            } else {
                if (dialectClazz != null && !"".equals(dialectClazz)) {
                    try {
                        Class<?> clazz = Class.forName(dialectClazz);
                        if (IDialect.class.isAssignableFrom(clazz)) {
                            dialect = (IDialect) clazz.newInstance();
                        }
                    } catch (ClassNotFoundException e) {
                        throw new MybatisPlusException("Class :" + dialectClazz + " is not found");
                    }
                }
            }
			
			/* 未配置方言则抛出异常 */
            if (dialect == null) {
                throw new MybatisPlusException("The value of the dialect property in mybatis configuration.xml is not defined.");
            }

			/*
			 * <p>
			 * 禁用内存分页
			 * </p>
			 * <p>
			 * 内存分页会查询所有结果出来处理（这个很吓人的），如果结果变化频繁这个数据还会不准。
			 * </p>
			 */
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            String originalSql = (String) boundSql.getSql();
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

            /**
             * <p>
             * 分页逻辑
             * </p>
             * <p>
             * 查询总记录数 count
             * </p>
             */
            if (rowBounds instanceof Pagination) {
                MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
                Connection connection = (Connection) invocation.getArgs()[0];
                Pagination page = this.count(originalSql, connection, mappedStatement, boundSql, (Pagination) rowBounds);
                originalSql = dialect.buildPaginationSql(originalSql, page.getOffsetCurrent(), page.getSize());
            }

            /**
             * 查询 SQL 设置
             */
            metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);
        }

        return invocation.proceed();
    }

    /**
     * 查询总记录条数
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    public Pagination count(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql,
                            Pagination page) {
        String sqlUse = sql;
        int order_by = sql.toUpperCase().lastIndexOf("ORDER BY");
        if (order_by > -1) {
            sqlUse = sql.substring(0, order_by);
        }
        StringBuffer countSql = new StringBuffer("SELECT COUNT(1) FROM (");
        countSql.append(sqlUse).append(") AS TOTAL");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql.toString());
           BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql.toString(),
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
                    boundSql.getParameterObject(), countBS);

            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if(parameterMappings != null ){
                for(ParameterMapping pm : parameterMappings ){
                    if(boundSql.getAdditionalParameter(pm.getProperty()) != null ){
                        countBS.setAdditionalParameter( pm.getProperty() , boundSql.getAdditionalParameter(pm.getProperty()) ) ;
                    }

                }
            }
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            int total = 0;
            if (rs.next()) {
                total = rs.getInt(1);
            }
            page.setTotal(total);

        } catch (SQLException e) {
            throw new RuntimeException("Execute count fail!" ,e) ;
        } finally {
            try {
                if(rs != null ){
                    rs.close();
                }
                if(pstmt != null ){
                    pstmt.close();
                }
            } catch (SQLException e) {
                //e.printStackTrace();
            }


        }
        return page;
    }

    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void setProperties(Properties prop) {
        String dialectType = prop.getProperty("dialectType");
        String dialectClazz = prop.getProperty("dialectClazz");
        if (dialectType != null && !"".equals(dialectType)) {
            this.dialectType = dialectType;
        }
        if (dialectClazz != null && !"".equals(dialectClazz)) {
            this.dialectClazz = dialectClazz;
        }
    }

    public void setDialectType(String dialectType) {
        this.dialectType = dialectType;
    }

    public void setDialectClazz(String dialectClazz) {
        this.dialectClazz = dialectClazz;
    }

}