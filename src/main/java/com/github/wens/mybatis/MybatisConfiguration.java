package com.github.wens.mybatis;

import com.github.wens.mybatis.mapper.AutoSqlInjector;
import com.github.wens.mybatis.support.mapper.CrudMapper;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.Set;
import java.util.logging.Logger;

/**
 * <p>
 * replace default Configuration class
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public class MybatisConfiguration extends Configuration {

    protected final Logger logger = Logger.getLogger("MybatisConfiguration");

    /**
     * 初始化调用
     */
    public MybatisConfiguration() {
        System.err.println("mybatis-plus init success.");
    }

    /**
     * <p>
     * MybatisPlus 加载 SQL 顺序：
     * </p>
     * 1、加载XML中的SQL<br>
     * 2、加载sqlProvider中的SQL<br>
     * 3、xmlSql 与 sqlProvider不能包含相同的SQL<br>
     * <br>
     * 调整后的SQL优先级：xmlSql > sqlProvider > crudSql <br>
     */
    @Override
    public void addMappedStatement(MappedStatement ms) {
        if (this.mappedStatements.containsKey(ms.getId())) {
            /*
             * 说明已加载了xml中的节点； 忽略mapper中的SqlProvider数据
			 */
            logger.severe("mapper[" + ms.getId() + "] is ignored, because it's exists, maybe from xml file");
            return;
        }
        super.addMappedStatement(ms);
    }

    @Override
    public void setDefaultScriptingLanguage(Class<?> driver) {
        if (driver == null) {
			/* 设置自定义 driver */
            driver = MybatisXMLLanguageDriver.class;
        }
        super.setDefaultScriptingLanguage(driver);
    }

    @Override
    public LanguageDriver getDefaultScriptingLanuageInstance() {
		/* 设置自定义 driver */
        return languageRegistry.getDriver(MybatisXMLLanguageDriver.class);
    }

    @Override
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return super.getMapper(type, sqlSession);
    }

    /**
     * 重新 addMapper 方法
     */
    @Override
    public <T> void addMapper(Class<T> type) {
        super.addMapper(type);

        if (!CrudMapper.class.isAssignableFrom(type)) {
            return;
        }

		/* 自动注入 SQL */
        new AutoSqlInjector(this).inject(type);
    }

    @Override
    public void addMappers(String packageName) {
        this.addMappers(packageName, Object.class);
    }

    @Override
    public void addMappers(String packageName, Class<?> superType) {
        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();
        resolverUtil.find(new ResolverUtil.IsA(superType), packageName);
        Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses();
        for (Class<?> mapperClass : mapperSet) {
            this.addMapper(mapperClass);
        }
    }
}
