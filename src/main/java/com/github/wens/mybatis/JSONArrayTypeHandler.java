package com.github.wens.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class JSONArrayTypeHandler extends BaseTypeHandler<JSONArray> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONArray objects, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, objects.toJSONString());
    }

    @Override
    public JSONArray getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseArray(resultSet.getString(s));
    }

    @Override
    public JSONArray getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseArray(resultSet.getString(i));
    }

    @Override
    public JSONArray getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseArray(callableStatement.getString(i));
    }

    private JSONArray parseArray(String value) {
        if (value == null) {
            return new JSONArray();
        } else {
            return JSON.parseArray(value);
        }
    }
}
