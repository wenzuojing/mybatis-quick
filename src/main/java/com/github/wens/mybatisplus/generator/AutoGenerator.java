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
package com.github.wens.mybatisplus.generator;

import com.github.wens.mybatisplus.annotations.IdType;
import com.github.wens.mybatisplus.exceptions.MybatisPlusException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 映射文件自动生成类
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public class AutoGenerator {

    private static String PATH_ENTITY = null;
    private static String PATH_MAPPER = null;
    private static String PATH_SERVICE = null;
    private static String PATH_SERVICE_IMPL = null;
    private static String PATH_XML = null;
    private ConfigGenerator config;
    public AutoGenerator() {

    }
    public AutoGenerator(ConfigGenerator config) {
        this.config = config;
    }

    /**
     * run 执行
     */
    public static void run(ConfigGenerator config) {
        if (config == null) {
            throw new MybatisPlusException(" ConfigGenerator is null. ");
        } else if (config.getIdType() == null) {
            throw new MybatisPlusException("ConfigGenerator IdType is null");
        }

        /**
         * 检查文件夹是否存在
         */
        File gf = new File(config.getSaveDir());
        if (!gf.exists()) {
            gf.mkdirs();
        }

        /**
         * 目录初始化
         */
        PATH_ENTITY = getFilePath(gf.getPath(), "entity");
        PATH_MAPPER = getFilePath(gf.getPath(), "mapper");
        PATH_XML = getFilePath(gf.getPath(), "xml");
        PATH_SERVICE = getFilePath(gf.getPath(), "service");
        PATH_SERVICE_IMPL = getFilePath(PATH_SERVICE, "impl");

       /* File fileEntity = new File(PATH_ENTITY) ;
        File fileMapper = new File(PATH_MAPPER) ;
        File fileXml = new File(PATH_XML) ;
        File fileService = new File(PATH_SERVICE) ;
        File fileServiceImp = new File(PATH_SERVICE_IMPL) ;

        if(!fileEntity.exists()){
            fileEntity.mkdirs();
        }
        if(!fileEntity.exists()){
            fileEntity.mkdirs();
        }
        if(!fileEntity.exists()){
            fileEntity.mkdirs();
        }
        if(!fileEntity.exists()){
            fileEntity.mkdirs();
        }
        if(!fileEntity.exists()){
            fileEntity.mkdirs();
        }*/

        /**
         * 开启生成映射关系
         */
        new AutoGenerator(config).generate();

        /**
         * 自动打开生成文件的目录
         * <p>
         * 根据 osName 执行相应命令
         * </p>
         */
        /*try {
            String osName = System.getProperty("os.name");
			if (osName != null) {
				if (osName.contains("Mac")) {
					Runtime.getRuntime().exec("open " + config.getSaveDir());
				} else if (osName.contains("Windows")) {
					Runtime.getRuntime().exec("cmd /c start " + config.getSaveDir());
				} else {
					System.err.println("save dir:" + config.getSaveDir());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        System.out.println(" generate success! ");

    }

    /**
     * 生成文件地址
     *
     * @param segment 文件地址片段
     * @return
     */
    private static String getFilePath(String savePath, String segment) {
        File folder = new File(savePath + File.separator + segment);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getPath();
    }

    public ConfigGenerator getConfig() {
        return config;
    }

    public void setConfig(ConfigGenerator config) {
        this.config = config;
    }

    /**
     * 生成映射文件
     * <p/>
     * 是否包含前缀，例如: tb_xxx 其中 tb_ 为前缀
     */
    public void generate() {
        Connection conn = null;
        try {
            /**
             * 创建连接
             */
            Class.forName(config.getDbDriverName());
            conn = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPassword());

            /**
             * 获取数据库表相关信息
             */
            List<String> tables = getTables(conn);
            Map<String, String> tableComments = getTableComment(conn);
            for (String table : tables) {
                List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
                Map<String, IdInfo> idMap = new HashMap<String, IdInfo>();
                ResultSet results = conn.prepareStatement("show full fields from " + table).executeQuery();
                while (results.next()) {
                    FieldInfo fieldInfo = new FieldInfo(results.getString("FIELD"), results.getString("TYPE"), results.getString("COMMENT"), table);
                    fieldInfos.add(fieldInfo);
                    String key = results.getString("KEY");
                    if ("PRI".equals(key)) {
                        boolean autoIncrement = false;
                        if ("auto_increment".equals(results.getString("EXTRA"))) {
                            autoIncrement = true;
                        }
                        idMap.put(fieldInfo.column, new IdInfo(fieldInfo, autoIncrement));
                    }
                }

                String beanName = getBeanName(table, config.isDbPrefix());
                String mapperName = beanName + "Mapper";
                String serviceName = "I" + beanName + "Service";
                String serviceImplName = beanName + "ServiceImpl";

                /**
                 * 生成映射文件
                 */
                buildEntityBean(fieldInfos, tableComments.get(table), idMap, table, beanName);
                buildMapper(beanName, mapperName, idMap);
                buildMapperXml(fieldInfos, idMap, mapperName);
                buildService(beanName, serviceName, idMap);
                buildServiceImpl(beanName, serviceImplName, serviceName, mapperName, idMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables(Connection conn) throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tablesName = results.getString(1);
            if (config.getTables() == null) {
                tables.add(tablesName);
            } else {

                for (String t : config.getTables()) {
                    if (t.equalsIgnoreCase(tablesName)) {
                        tables.add(tablesName);
                    }
                }

            }

        }
        return tables;
    }

    /**
     * 生成 beanName
     *
     * @param table 表名
     * @return beanName
     */
    private String getBeanName(String table, boolean includePrefix) {
        StringBuffer sb = new StringBuffer();
        if (table.contains("_")) {
            String[] tables = table.split("_");
            int l = tables.length;
            int s = 0;
            if (includePrefix) {
                s = 1;
            }
            for (int i = s; i < l; i++) {
                String temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1).toLowerCase());
            }
        } else {
            sb.append(table.substring(0, 1).toUpperCase()).append(table.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 字段类型转换
     *
     * @param type 字段类型
     * @return
     */
    private String processType(String type) {
        if (type.indexOf("char") > -1) {
            return "String";
        } else if (type.indexOf("bigint") > -1) {
            return "Long";
        } else if (type.indexOf("int") > -1) {
            return "Integer";
        } else if (type.indexOf("date") > -1 || type.indexOf("timestamp") > -1) {
            return "Date";
        } else if (type.indexOf("text") > -1) {
            return "String";
        } else if (type.indexOf("bit") > -1) {
            return "Boolean";
        } else if (type.indexOf("decimal") > -1) {
            return "BigDecimal";
        } else if (type.indexOf("blob") > -1) {
            return "byte[]";
        } else if (type.indexOf("float") > -1) {
            return "Float";
        } else if (type.indexOf("double") > -1) {
            return "Double";
        }
        return null;
    }


    private boolean hasDate(List<FieldInfo> fieldInfos) {
        int size = fieldInfos.size();
        for (int i = 0; i < size; i++) {
            String type = fieldInfos.get(i).type;
            if (type.indexOf("date") > -1 || type.indexOf("timestamp") > -1) {
                return true;
            }
        }
        return false;
    }


    private boolean hasDecimal(List<FieldInfo> fieldInfos) {
        int size = fieldInfos.size();
        for (int i = 0; i < size; i++) {
            String type = fieldInfos.get(i).type;
            if (type.indexOf("decimal") > -1) {
                return true;
            }
        }
        return false;
    }

    private String processField(String field) {
		/*
		 * 驼峰命名直接返回
		 */
        if (config.isColumnHump()) {
            return field;
        }
		
		/* 
		 * 处理下划线分割命名字段
		 */
        StringBuffer sb = new StringBuffer(field.length());
        String[] fields = field.split("_");
        sb.append(fields[0].toLowerCase());
        for (int i = 1; i < fields.length; i++) {
            String temp = fields[i];
            sb.append(temp.substring(0, 1).toUpperCase());
            sb.append(temp.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" *");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" *");
        bw.newLine();
        bw.write(" */");
        return bw;
    }

    /**
     * 生成实体类
     *
     * @param fieldInfos
     * @throws IOException
     */
    private void buildEntityBean(List<FieldInfo> fieldInfos, String tableComment,
                                 Map<String, IdInfo> idMap, String table, String beanName) throws IOException {
        File beanFile = new File(PATH_ENTITY, beanName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + config.getEntityPackage() + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        if (hasDate(fieldInfos)) {
            bw.write("import java.util.Date;");
            bw.newLine();
        }
        if (hasDecimal(fieldInfos)) {
            bw.write("import java.math.BigDecimal;");
            bw.newLine();
        }
        bw.newLine();
        if (config.getIdType() != IdType.ID_WORKER) {
            bw.write("import com.github.wens.mybatisplus.annotations.IdType;");
            bw.newLine();
        }
        bw.write("import com.github.wens.mybatisplus.annotations.TableField;");
        bw.newLine();
        bw.write("import com.github.wens.mybatisplus.annotations.TableId;");
        bw.newLine();
        bw.write("import com.github.wens.mybatisplus.annotations.TableName;");
        bw.newLine();
        bw.write("import com.github.wens.mybatisplus.examples.Example;");
        bw.newLine();
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("@TableName(value = \"" + table + "\")");
        bw.newLine();
        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        bw.write("\t@TableField(exist = false)");
        bw.newLine();
        bw.write("\tprivate static final long serialVersionUID = 1L;");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic static Example<"+beanName+"> createExample(){");
        bw.newLine();
        bw.write("\t\treturn new Example<"+beanName+">("+beanName+".class);");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        int size = fieldInfos.size();
        for (int i = 0; i < size; i++) {
            bw.newLine();
            bw.write("\t/** " + fieldInfos.get(i).comment + " */");
            bw.newLine();
			/*
			 * 判断ID 添加注解
			 * <br>
			 * isLine 是否包含下划线
			 */
            String column = fieldInfos.get(i).column;
            String field = processField(column);
            boolean isLine = column.contains("_");
            IdInfo idInfo = idMap.get(column);
            if (idInfo != null) {
                //@TableId(value = "test_id", type = IdType.AUTO_INCREMENT)
                bw.write("\t@TableId");
                String idType = toIdType(idInfo);
                if (idInfo.autoIncrement) {
                    System.err.println(" Table :{ " + table + " } ID is Auto increment");
                    if (isLine) {
                        bw.write("(value = \"" + column + "\"");
                        if (idType != null) {
                            bw.write(", ");
                            bw.write(idType);
                        }
                        bw.write(")");
                    } else if (idType != null) {
                        bw.write("(");
                        bw.write(idType);
                        bw.write(")");
                    }
                } else {
                    if (isLine) {
                        bw.write("(value = \"" + column + "\"");
                        if (idType != null) {
                            bw.write(", ");
                            bw.write(idType);
                        }
                        bw.write(")");
                    } else if (idType != null) {
                        bw.write("(");
                        bw.write(idType);
                        bw.write(")");
                    }
                }
                bw.newLine();
            } else if (isLine) {
                //@TableField(value = "test_type", exist = false)
                bw.write("\t@TableField(value = \"" + column + "\")");
                bw.newLine();
            }
            bw.write("\tprivate " + processType(fieldInfos.get(i).type) + " " + field + ";");
            bw.newLine();
        }

		/*
		 * 生成get 和 set方法
		 */
        for (int i = 0; i < size; i++) {
            String _tempType = processType(fieldInfos.get(i).type);
            String _tempField = processField(fieldInfos.get(i).column);
            String _field = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            bw.write("\tpublic " + _tempType + " get" + _field + "() {");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic void set" + _field + "(" + _tempType + " " + _tempField + ") {");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }

        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public String toIdType(IdInfo idInfo) {
        if (config.getIdType() == IdType.AUTO) {
            return "type = IdType.AUTO";
        } else if (config.getIdType() == IdType.INPUT) {
            return "type = IdType.INPUT";
        }
        return null;
    }

    /**
     * 构建Mapper文件
     *
     * @param beanName
     * @param mapperName
     * @throws IOException
     */
    private void buildMapper(String beanName, String mapperName, Map<String, IdInfo> idMap) throws IOException {
        File mapperFile = new File(PATH_MAPPER, mapperName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + config.getMapperPackage() + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + config.getEntityPackage() + "." + beanName + ";");
        bw.newLine();
        bw.write("import com.github.wens.mybatisplus.mapper.AutoMapper;");
        bw.newLine();

        bw = buildClassComment(bw, beanName + " 表数据库控制层接口");
        bw.newLine();


        bw.write("public interface " + mapperName + " extends AutoMapper<" + beanName + "," + getIdType(idMap) + "> {");
        bw.newLine();
        bw.newLine();

        // ----------定义mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    private String getIdType(Map<String, IdInfo> idMap) {
        IdInfo[] ids = idMap.values().toArray(new IdInfo[idMap.size()]);
        if (ids.length == 0) {
            return "String" ;
        }
        String type = ids[0].fieldInfo.type;

        if (type.indexOf("char") > -1 || type.indexOf("text") > -1) {
            return "String";
        } else if (type.indexOf("int") > -1) {
            return "Long";
        } else {
            throw new UnsupportedOperationException("Not support type for id:" + type);
        }
    }


    private void buildMapperXml(List<FieldInfo> fieldInfos,
                                Map<String, IdInfo> idMap, String mapperName) throws IOException {
        File mapperXmlFile = new File(PATH_XML, mapperName + ".xml");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + config.getMapperPackage() + "." + mapperName + "\">");
        bw.newLine();
        bw.newLine();

		/*
		 * 下面开始写SqlMapper中的方法
		 */
        buildSQL(bw, idMap, fieldInfos);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    /**
     * 构建service文件
     *
     * @param beanName
     * @param serviceName
     * @throws IOException
     */
    private void buildService(String beanName, String serviceName, Map<String, IdInfo> idMap) throws IOException {
        File serviceFile = new File(PATH_SERVICE, serviceName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(serviceFile), "utf-8"));
        bw.write("package " + config.getServicePackage() + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + config.getEntityPackage() + "." + beanName + ";");
        bw.newLine();
        bw.write("import com.github.wens.framework.service.ISuperService;");
        bw.newLine();

        bw = buildClassComment(bw, beanName + " 表数据服务层接口");
        bw.newLine();
        bw.write("public interface " + serviceName + " extends ISuperService<" + beanName + "," + getIdType(idMap) + "> {");
        bw.newLine();
        bw.newLine();

        // ----------定义service中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 构建service实现类文件
     *
     * @param beanName
     * @param serviceImplName
     * @param mapperName
     * @throws IOException
     */
    private void buildServiceImpl(String beanName, String serviceImplName, String serviceName, String mapperName, Map<String, IdInfo> idMap) throws IOException {
        File serviceFile = new File(PATH_SERVICE_IMPL, serviceImplName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(serviceFile), "utf-8"));
        bw.write("package " + config.getServicePackage() + ".impl;");
        bw.newLine();
        bw.newLine();
        bw.write("import org.springframework.stereotype.Service;");
        bw.newLine();
        bw.newLine();
        bw.write("import " + config.getMapperPackage() + "." + mapperName + ";");
        bw.newLine();
        bw.write("import " + config.getEntityPackage() + "." + beanName + ";");
        bw.newLine();
        bw.write("import " + config.getServicePackage() + "." + serviceName + ";");
        bw.newLine();

        String superServiceImpl = config.getSuperServiceImpl();
        bw.write("import " + superServiceImpl + ";");
        bw.newLine();

        bw = buildClassComment(bw, beanName + " 表数据服务层接口实现类");
        bw.newLine();
        bw.write("@Service");
        bw.newLine();
        superServiceImpl = superServiceImpl.substring(superServiceImpl.lastIndexOf(".") + 1);
        bw.write("public class " + serviceImplName + " extends " + superServiceImpl
                + "<" + mapperName + ", " + beanName + "," + getIdType(idMap) + "> implements " + serviceName + " {");
        bw.newLine();
        bw.newLine();

        // ----------定义service中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }


    private void buildSQL(BufferedWriter bw, Map<String, IdInfo> idMap, List<FieldInfo> fieldInfos) throws IOException {
        int size = fieldInfos.size();
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        String shortTableName  = shortTableName(fieldInfos.get(0).table);

        for (int i = 0; i < size; i++) {
            String column = fieldInfos.get(i).column;
            IdInfo idInfo = idMap.get(column);
            if (idInfo != null) {
                bw.write("\t\t " + shortTableName+"."+column + " AS " + processField(column));
            } else {
                bw.write(" " + shortTableName+"."+column + " AS " + processField(column));
            }
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();
    }

    private String shortTableName(String tableName) {
        return "" + tableName.charAt(0);
    }

    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment(Connection conn) throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            maps.put(results.getString("NAME"), results.getString("COMMENT"));
        }
        return maps;
    }

    class FieldInfo {
        String column;
        String type;
        String comment;
        String table;

        public FieldInfo(String column, String type, String comment, String table) {
            this.column = column;
            this.type = type;
            this.comment = comment;
            this.table = table;
        }
    }

    class IdInfo {
        FieldInfo fieldInfo;
        boolean autoIncrement;

        public IdInfo(FieldInfo fieldInfo, boolean autoIncrement) {
            this.fieldInfo = fieldInfo;
            this.autoIncrement = autoIncrement;

        }

    }
}
