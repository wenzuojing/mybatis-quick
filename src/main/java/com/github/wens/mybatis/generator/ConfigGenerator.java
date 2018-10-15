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
package com.github.wens.mybatis.generator;

import com.github.wens.mybatis.annotations.IdType;

import java.util.List;

/**
 * <p>
 * 生成器配置类
 * ********************************* 使用前必读 *********************
 * saveDir 文件生成目录
 * entity_package entity 包路径
 * mapper_package mapper 包路径
 * servicePackage service 包路径
 * superServiceImpl service 父类包路径
 * -------------------------------------
 * 以下数据库相关配置：
 * -------------------------------------
 * db_include_prefix 表是否包含前缀，例如: tb_xxx 其中 tb_ 为前缀
 * db_driverName 驱动
 * db_user 用户名
 * db_password 密码
 * db_url 连接地址
 * *************************************************************
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public class ConfigGenerator {

    private String saveDir;

    private String entityPackage;

    private String mapperPackage;

    private String servicePackage;

    private String superServiceImpl;

    /* db_config */
    private boolean dbPrefix = false;

    private String dbDriverName;

    private String dbUser;

    private String dbPassword;

    private String dbUrl;

    private IdType idType = null;

    /*
     * 字段是否为驼峰命名（默认 true）
     */
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
