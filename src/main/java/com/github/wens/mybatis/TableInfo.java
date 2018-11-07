package com.github.wens.mybatis;

import com.github.wens.mybatis.annotation.IdType;

import java.util.List;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class TableInfo {


    private IdType idType;


    private String tableName;


    private boolean keyRelated = false;


    private String keyProperty;


    private String keyColumn;


    private List<TableFieldInfo> fieldList;


    public IdType getIdType() {
        return idType;
    }


    public void setIdType(IdType idType) {
        this.idType = idType;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public boolean isKeyRelated() {
        return keyRelated;
    }


    public void setKeyRelated(boolean keyRelated) {
        this.keyRelated = keyRelated;
    }


    public String getKeyProperty() {
        return keyProperty;
    }


    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }


    public String getKeyColumn() {
        return keyColumn;
    }


    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }


    public List<TableFieldInfo> getFieldList() {
        return fieldList;
    }


    public void setFieldList(List<TableFieldInfo> fieldList) {
        this.fieldList = fieldList;
    }

    public String getColumn(String property) {

        for (TableFieldInfo tfi : fieldList) {
            if (tfi.getProperty().equals(property)) {
                return tfi.getColumn();
            }
        }

        return keyProperty.equals(property) ? keyColumn : null;
    }

    public String getProperty(String column) {

        for (TableFieldInfo tfi : fieldList) {
            if (tfi.getColumn().equals(column)) {
                return tfi.getProperty();
            }
        }
        return keyColumn.equals(column) ? column : null;
    }
}
