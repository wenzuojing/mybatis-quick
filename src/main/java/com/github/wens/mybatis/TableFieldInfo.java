package com.github.wens.mybatis;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class TableFieldInfo {

    private boolean related;


    private String column;


    private String property;


    public TableFieldInfo(boolean related, String column, String property) {
        this.related = related;
        this.column = column;
        this.property = property;
    }


    public TableFieldInfo(String column) {
        this.related = false;
        this.column = column;
        this.property = column;
    }


    public boolean isRelated() {
        return related;
    }


    public void setRelated(boolean related) {
        this.related = related;
    }


    public String getColumn() {
        return column;
    }


    public void setColumn(String column) {
        this.column = column;
    }


    public String getProperty() {
        return property;
    }


    public void setProperty(String property) {
        this.property = property;
    }

}
