package com.github.wens.mybatis.mapper;

import com.github.wens.mybatis.MybatisXMLLanguageDriver;
import com.github.wens.mybatis.annotations.IdType;
import com.github.wens.mybatis.support.mapper.CrudMapper;
import com.github.wens.mybatis.toolkit.TableInfo;
import com.github.wens.mybatis.toolkit.TableInfoHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class InjectCrudMappedStatement {


    private Configuration configuration;


    public InjectCrudMappedStatement(Configuration configuration) {
        this.configuration = configuration;
    }


    public void inject(Class<?> mapperClass) {
        Class<?> modelClass = resolveModelClass(mapperClass);
        if (modelClass == null) {
            return;
        }

        TableInfo table = TableInfoHelper.getTableInfo(modelClass);
        if (table.getKeyProperty() == null) {
            return;
        }

        MapperBuilderAssistant assistant = new MapperBuilderAssistant(configuration, mapperClass.getName().replaceAll("\\.", "/"));
        assistant.setCurrentNamespace(mapperClass.getName());

        this.injectInsert(assistant, mapperClass, modelClass, table);
        this.injectInsertSelective(assistant, mapperClass, modelClass, table);
        this.injectInsertBatch(assistant, mapperClass, modelClass, table);

        this.injectDeleteById(assistant, mapperClass, modelClass, table);
        this.injectDeleteByIds(assistant, mapperClass, modelClass, table);
        this.injectDeleteByExample(assistant, mapperClass, modelClass, table);

        this.injectUpdateById(assistant, mapperClass, modelClass, table);
        this.injectUpdateSelectiveById(assistant, mapperClass, modelClass, table);
        this.injectUpdateSelectiveByExample(assistant, mapperClass, modelClass, table);

        this.injectSelectById(assistant, mapperClass, modelClass, table);
        this.injectSelectByIds(assistant, mapperClass, modelClass, table);
        this.injectSelectOneByExample(assistant, mapperClass, modelClass, table);
        this.injectSelectListByExample(assistant, mapperClass, modelClass, table);
        this.injectSelectPageByExample(assistant, mapperClass, modelClass, table);



    }

    private void injectSelectPageByExample(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        addSelectMappedStatement(assistant,mapperClass,modelClass,table,"selectPageByExample","${ex.selectColumns}",exampleWhereClause(),exampleOrderByClause(),"");
    }

    private void injectSelectListByExample(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        addSelectMappedStatement(assistant,mapperClass,modelClass,table,"selectListByExample","${ex.selectColumns}",exampleWhereClause(),exampleWhereClause(),"");
    }

    private void injectSelectOneByExample(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        addSelectMappedStatement(assistant,mapperClass,modelClass,table,"selectOneByExample","${ex.selectColumns}",exampleWhereClause(),exampleOrderByClause(),"limit 1");
    }

    private void injectSelectByIds(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        addSelectMappedStatement(assistant,mapperClass,modelClass,table,"selectByIds",allSelectColumn(table),wrapWhere(table.getKeyColumn() +" IN (" + wrapForeach("#{item}","item","index" ,"ids",",") +")" ),"","");
    }

    private String allSelectColumn(TableInfo table) {
        return table.getKeyColumn() +" AS " + table.getKeyProperty()+"," + Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> f.getColumn() + " AS " + f.getProperty()));
    }

    private void injectSelectById(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        addSelectMappedStatement(assistant,mapperClass,modelClass,table,"selectById",allSelectColumn(table),wrapWhere( table.getKeyColumn() +" = #{id}" ),"","");
    }

    private void injectUpdateSelectiveByExample(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        String setColumns = wrapTrim(Joiner.on("").join(Lists.transform(table.getFieldList(), f -> "<if test=\"et." + f.getProperty() + "!=null\"> "+f.getColumn()+" = #{et." + f.getProperty() + "},</if>")),"","",",");
        String where = exampleWhereClause();
        addUpdateMappedStatement(assistant, mapperClass, modelClass, table, "updateSelectiveByExample",setColumns, where);
    }

    private void injectUpdateSelectiveById(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        String setColumns = wrapTrim(Joiner.on("").join(Lists.transform(table.getFieldList(), f -> "<if test=\"et." + f.getProperty() + "!=null\"> "+f.getColumn()+" = #{et." + f.getProperty() + "},</if>")),"","",",");
        String where = wrapWhere(table.getKeyColumn() + " = #{et."+table.getKeyProperty()+"}" );
        addUpdateMappedStatement(assistant, mapperClass, modelClass, table, "updateSelectiveById",setColumns, where);
    }

    private void injectUpdateById(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        String setColumns = Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> f.getColumn() + " = #{et." + f.getProperty() + "}"));
        String where = wrapWhere(table.getKeyColumn() + " = #{et."+table.getKeyProperty()+"}"  );
        addUpdateMappedStatement(assistant, mapperClass, modelClass, table, "updateById",setColumns, where);
    }

    private void injectDeleteByExample(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String where = exampleWhereClause();
        addDeleteMappedStatement(assistant, mapperClass, modelClass, table, "deleteByExample", where);

    }

    private void injectDeleteByIds(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String where = wrapWhere(table.getKeyColumn() + " IN " + wrapForeach("#{item}", "item", "index", "list", ","));
        addDeleteMappedStatement(assistant, mapperClass, modelClass, table, "deleteByIds", where);
    }

    private void injectDeleteById(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String where = wrapWhere(table.getKeyColumn() + " = #{" + table.getKeyProperty() + "}");
        addDeleteMappedStatement(assistant, mapperClass, modelClass, table, "deleteById", where);

    }

    private void injectInsertBatch(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String columns = Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> f.getColumn()));
        String placeholders = Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> "#{item." + f.getProperty() + "}"));
        if (table.getIdType() != IdType.AUTO) {
            columns = table.getKeyColumn() + "," + columns;
            placeholders = "#{item." + table.getKeyProperty() + "}," + placeholders;
        }

        columns = wrapTrim(columns, "(", ")", ",");
        placeholders = wrapForeach(wrapTrim(placeholders, "(", ")", ","), "item", "index", "list", ",");
        addInsertMappedStatement(assistant, mapperClass, modelClass, table, "insertBatch", columns, placeholders);


    }

    private void injectInsertSelective(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String columns = Joiner.on("").join(Lists.transform(table.getFieldList(), f -> "<if test=\"" + f.getColumn() + "!=null\">" + f.getColumn() + ",</if>"));
        String placeholders = Joiner.on("").join(Lists.transform(table.getFieldList(), f -> "<if test=\"" + f.getProperty() + "!=null\">#{" + f.getProperty() + "},</if>"));
        if (table.getIdType() != IdType.AUTO) {
            columns = table.getKeyColumn() + "," + columns;
            placeholders = "#{" + table.getKeyProperty() + "}," + placeholders;
        }

        columns = wrapTrim(columns, "(", ")", ",");
        placeholders = wrapTrim(placeholders, "(", ")", ",");
        addInsertMappedStatement(assistant, mapperClass, modelClass, table, "insertSelective", columns, placeholders);

    }

    private void injectInsert(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String columns = Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> f.getColumn()));
        String placeholders = Joiner.on(",").join(Lists.transform(table.getFieldList(), f -> "#{" + f.getProperty() + "}"));
        if (table.getIdType() != IdType.AUTO) {
            columns = table.getKeyColumn() + "," + columns ;
            placeholders = "#{" + table.getKeyProperty() + "}," + placeholders;
        }

        columns = wrapTrim(columns, "(", ")", ",");
        placeholders = wrapTrim(placeholders, "(", ")", ",");
        addInsertMappedStatement(assistant, mapperClass, modelClass, table, "insert", columns, placeholders);
    }

    private MappedStatement addSelectMappedStatement(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table, String id,String selectColumns,String where , String order,String limit ) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("SELECT ").append(selectColumns).append(" FROM ").append(table.getTableName()).append(" ").append(where).append(" ").append(order).append(" ").append(limit);
        SqlSource sqlSource = configuration.getDefaultScriptingLanguageInstance().createSqlSource(configuration, wrapScript(sb.toString()), modelClass);
        return this.addMappedStatement(assistant, mapperClass, id, sqlSource, SqlCommandType.SELECT, modelClass, modelClass, new NoKeyGenerator(), null, null);
    }

    private MappedStatement addUpdateMappedStatement(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table, String id,String setColumns, String where) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("UPDATE ").append(table.getTableName()).append(" SET ").append(setColumns).append(where);
        SqlSource sqlSource = configuration.getDefaultScriptingLanguageInstance().createSqlSource(configuration, wrapScript(sb.toString()), modelClass);
        return this.addMappedStatement(assistant, mapperClass, id, sqlSource, SqlCommandType.UPDATE, modelClass, null, new NoKeyGenerator(), null, null);
    }

    private MappedStatement addDeleteMappedStatement(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table, String id, String where) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("DELETE FROM").append(" ").append(table.getTableName()).append(" ").append(where);
        SqlSource sqlSource = configuration.getDefaultScriptingLanguageInstance().createSqlSource(configuration, wrapScript(sb.toString()), modelClass);
        return this.addMappedStatement(assistant, mapperClass, id, sqlSource, SqlCommandType.DELETE, modelClass, null, new NoKeyGenerator(), null, null);
    }

    private MappedStatement addInsertMappedStatement(MapperBuilderAssistant assistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table, String id, String columns, String placeholders) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("INSERT INTO").append(" ").append(table.getTableName()).append(" ").append(columns).append(" VALUES ").append(placeholders);
        SqlSource sqlSource = configuration.getDefaultScriptingLanguageInstance().createSqlSource(configuration, wrapScript(sb.toString()), modelClass);
        return this.addMappedStatement(assistant, mapperClass, id, sqlSource, SqlCommandType.INSERT, modelClass, null, table.getIdType() == IdType.AUTO ? new Jdbc3KeyGenerator() : new NoKeyGenerator(), table.getKeyProperty(), table.getKeyColumn());
    }

    private MappedStatement addMappedStatement(MapperBuilderAssistant assistant, Class<?> mapperClass, String id, SqlSource sqlSource,
                                               SqlCommandType sqlCommandType, Class<?> parameterClass, Class<?> resultType, KeyGenerator keyGenerator,
                                               String keyProperty, String keyColumn) {
        String statementName = mapperClass.getName() + "." + id;
        if (configuration.hasStatement(statementName)) {
            System.err.println("{" + statementName + "} Has been loaded by XML or SqlProvider, ignoring the injection of the SQL.");
            return null;
        }
        return assistant.addMappedStatement(id, sqlSource, StatementType.PREPARED, sqlCommandType, null, null, null,
                parameterClass, null, resultType, null, false, true, false, keyGenerator, keyProperty, keyColumn,
                configuration.getDatabaseId(), new MybatisXMLLanguageDriver(), null);
    }


    private String wrapScript(String src) {
        return "<script>" + src + "</script>";
    }

    private String wrapWhere(String src) {
        return "<where>" + src + "</where>";
    }

    private String wrapTrim(String src, String prefix, String suffix, String suffixOverrides) {
        return "<trim prefix=\"" + prefix + "\" suffix=\"" + suffix + "\" suffixOverrides=\"" + suffixOverrides + "\">" + src + "</trim>";
    }

    private String wrapForeach(String src, String item, String index, String collection) {
        return this.wrapForeach(src, item, index, collection, "");
    }

    private String wrapForeach(String src, String item, String index, String collection, String separator) {
        return "<foreach item=\"" + item + "\" index=\"" + index + "\" collection=\"" + collection + "\" separator=\"" + separator + "\">" + src + "</foreach>";
    }

    public String exampleWhereClause() {
        return "<if test=\"ex != null\">" +
                "<where>\n" +
                "  <foreach collection=\"ex.oredCriteria\" item=\"criteria\" separator=\"or\">\n" +
                "    <if test=\"criteria.valid\">\n" +
                "      <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
                "        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" +
                "          <choose>\n" +
                "            <when test=\"criterion.noValue\">\n" +
                "              and ${criterion.condition}\n" +
                "            </when>\n" +
                "            <when test=\"criterion.singleValue\">\n" +
                "              and ${criterion.condition} #{criterion.value}\n" +
                "            </when>\n" +
                "            <when test=\"criterion.betweenValue\">\n" +
                "              and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}\n" +
                "            </when>\n" +
                "            <when test=\"criterion.listValue\">\n" +
                "              and ${criterion.condition}\n" +
                "              <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n" +
                "                #{listItem}\n" +
                "              </foreach>\n" +
                "            </when>\n" +
                "          </choose>\n" +
                "        </foreach>\n" +
                "      </trim>\n" +
                "    </if>\n" +
                "  </foreach>\n" +
                "</where>" +
                "</if>";
    }

    public String exampleOrderByClause() {
        return "<if test=\"ex != null\">" +
                "<if test=\"ex.orderByClause!=null\">\n order by ${ex.orderByClause}\n</if>" +
                "</if>";
    }

    private Class<?> resolveModelClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        ParameterizedType target = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(CrudMapper.class)) {
                target = (ParameterizedType) type;
                break;
            }
        }

        if (target == null) {
            return null;
        }

        Type[] parameters = target.getActualTypeArguments();
        Class<?> modelClass = (Class<?>) parameters[0];
        return modelClass;
    }
}
