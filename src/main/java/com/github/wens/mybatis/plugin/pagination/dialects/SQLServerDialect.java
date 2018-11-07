package com.github.wens.mybatis.plugin.pagination.dialects;

import com.github.wens.mybatis.plugin.pagination.IDialect;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class SQLServerDialect implements IDialect {

    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuffer sql = new StringBuffer(originalSql);
        sql.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ");
        sql.append(limit).append(" ROWS ONLY");
        return sql.toString();
    }

}
