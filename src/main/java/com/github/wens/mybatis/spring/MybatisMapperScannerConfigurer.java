package com.github.wens.mybatis.spring;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Collection;

public class MybatisMapperScannerConfigurer extends MapperScannerConfigurer {

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        super.postProcessBeanDefinitionRegistry(registry);

        MapperRegistry mapperRegistry = sqlSessionFactory.getConfiguration().getMapperRegistry();
        Collection<Class<?>> mappers = mapperRegistry.getMappers();
        System.out.println(mappers);


    }
}
