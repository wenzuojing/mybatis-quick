package com.github.wens.mybatis.plugin.pagination.dialects;

import com.github.wens.mybatis.plugin.pagination.IDialect;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class SQLiteDialect implements IDialect {

    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuffer sql = new StringBuffer(originalSql);
        sql.append(" limit ").append(limit).append(" offset ").append(offset);
        return sql.toString();
    }

}
