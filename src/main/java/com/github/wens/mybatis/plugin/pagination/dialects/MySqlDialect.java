package com.github.wens.mybatis.plugin.pagination.dialects;

import com.github.wens.mybatis.plugin.pagination.IDialect;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class MySqlDialect implements IDialect {

    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" LIMIT ").append(offset).append(",").append(limit);
        return sql.toString();
    }

}
