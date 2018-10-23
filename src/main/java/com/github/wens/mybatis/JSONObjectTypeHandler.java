package com.github.wens.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i ,jsonObject.toJSONString() );
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseObject(resultSet.getString(s)) ;
    }


    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseObject(resultSet.getString(i)) ;
    }

    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseObject(callableStatement.getString(i)) ;
    }


    private JSONObject parseObject(String value ) {
        if(value == null ){
            return new JSONObject();
        }else{
            return JSON.parseObject(value);
        }
    }
}
