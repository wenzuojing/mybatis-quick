package com.github.wens.mybatis.generator;

import com.github.wens.mybatis.annotation.IdType;

import java.util.List;

/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class Configuration {

    private String saveDir;

    private String entityPackage;

    private String mapperPackage;

    private String servicePackage;

    private String superServiceImpl;

    private boolean dbPrefix = false;

    private String dbDriverName;

    private String dbUser;

    private String dbPassword;

    private String dbUrl;

    private IdType idType = null;

    private boolean isColumnHump = true;

    private List<String> tables;


    public String getSaveDir() {
        return saveDir;
    }


    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }


    public String getEntityPackage() {
        return entityPackage;
    }


    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }


    public String getMapperPackage() {
        return mapperPackage;
    }


    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }


    public String getServicePackage() {
        return servicePackage;
    }


    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }


    public String getSuperServiceImpl() {
        if (superServiceImpl == null || "".equals(superServiceImpl)) {
            return "com.github.wens.mybatis.support.service.impl.SuperServiceImpl";
        }
        return superServiceImpl;
    }


    public void setSuperServiceImpl(String superServiceImpl) {
        this.superServiceImpl = superServiceImpl;
    }


    public boolean isDbPrefix() {
        return dbPrefix;
    }


    public void setDbPrefix(boolean dbPrefix) {
        this.dbPrefix = dbPrefix;
    }


    public String getDbDriverName() {
        return dbDriverName;
    }


    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }


    public String getDbUser() {
        return dbUser;
    }


    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }


    public String getDbPassword() {
        return dbPassword;
    }


    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }


    public String getDbUrl() {
        return dbUrl;
    }


    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }


    public IdType getIdType() {
        return idType;
    }


    public void setIdType(IdType idType) {
        this.idType = idType;
    }


    public boolean isColumnHump() {
        return isColumnHump;
    }


    public void setColumnHump(boolean isColumnHump) {
        this.isColumnHump = isColumnHump;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }
}
