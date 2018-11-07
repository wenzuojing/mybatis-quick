package com.github.wens.mybatis.plugin.pagination.dialects;

import com.github.wens.mybatis.plugin.pagination.IDialect;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class OracleDialect implements IDialect {

    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        /*
         * ORACLE 分页是通过 ROWNUMBER 进行的，ROWNUMBER 是从 1 开始的
		 */
        offset++;
        sql.insert(0, "SELECT U.*, ROWNUM R FROM (").append(") U WHERE ROWNUM < ").append(offset + limit);
        sql.insert(0, "SELECT * FROM (").append(") TEMP WHERE R >= ").append(offset);
        return sql.toString();
    }

}
