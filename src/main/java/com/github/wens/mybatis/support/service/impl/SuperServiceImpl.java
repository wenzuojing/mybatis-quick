package com.github.wens.mybatis.support.service.impl;

import com.github.wens.mybatis.support.mapper.CrudMapper;
import com.github.wens.mybatis.support.service.ISuperService;
import com.github.wens.mybatis.examples.Example;
import com.github.wens.mybatis.exceptions.MybatisQuickException;
import com.github.wens.mybatis.support.Page;
import com.github.wens.mybatis.toolkit.TableInfo;
import com.github.wens.mybatis.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class SuperServiceImpl<M extends CrudMapper<T, I>, T, I> implements ISuperService<T, I> {

    protected Class<T> entityClass;

    protected TableInfo table;

    @Autowired
    protected M mapper;

    public   SuperServiceImpl(){
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(SuperServiceImpl.class) ){
            Type[] parameters = ((ParameterizedType) type).getActualTypeArguments();
            entityClass  = (Class<T>) parameters[1];
            table = TableInfoHelper.getTableInfo(entityClass);
        }
    }

    protected boolean retBool(int result) {
        return (result >= 1) ? true : false;
    }


    @Override
    public long countByExample(Example<T> example) {
        return mapper.countByExample(example);
    }

    public boolean insert(T entity) {
        return retBool(mapper.insert(entity));
    }


    public boolean insertSelective(T entity) {
        return retBool(mapper.insertSelective(entity));
    }


    public boolean insertBatch(List<T> entityList) {
        return retBool(mapper.insertBatch(entityList));
    }


    public boolean deleteById(I id) {
        return retBool(mapper.deleteById(id));
    }


    public boolean deleteByExample(Example<T> example) {
        return retBool(mapper.deleteByExample(example));
    }

    public boolean deleteBatchIds(List<I> idList) {
        return retBool(mapper.deleteByIds(idList));
    }


    public boolean updateById(T entity) {
        return retBool(mapper.updateById(entity));
    }


    public boolean updateSelectiveById(T entity) {
        return retBool(mapper.updateSelectiveById(entity));
    }


    public boolean updateSelectiveByExample(T entity, Example<T> example) {
        return retBool(mapper.updateSelectiveByExample(entity, example));
    }


    public T findById(I id) {
        return mapper.selectById(id);
    }

    @Override
    public <E> E findById(I id, Class<E> entityClass) {
        return null;
    }

    public List<T> findByIds(List<I> ids) {
        if(ids == null || ids.isEmpty() ){
            return Collections.EMPTY_LIST ;
        }
        return mapper.selectByIds(ids);
    }

    @Override
    public <E> List<E> findByIds(List<I> ids, Class<E> entityClass) {
        if(ids == null || ids.size() == 0 ){
            return Collections.EMPTY_LIST ;
        }
        Example<T> example = Example.of(this.entityClass);
        example.createCriteria().andIn(this.table.getKeyProperty(),ids);
        List<T> list = this.findListByExample(example);
        if(list == null ){
            return null ;
        }
        return Lists.transform(list, item -> {
            E e = instance(entityClass);
            BeanUtils.copyProperties(item,e);
            return e ;
        });
    }

    @Override
    public T findOneByExample(Example<T> example) {
        return mapper.selectOneByExample(example);
    }

    @Override
    public <E> E findOneByExample(Example<T> example, Class<E> entityClass) {
        example.selectProperties(selectProperties(entityClass));
        T one = findOneByExample(example);
        if(one == null ){
            return null ;
        }
        E e = instance(entityClass);
        BeanUtils.copyProperties(one,e);
        return e ;
    }

    public List<T> findListByExample(Example<T> example) {
        return mapper.selectListByExample(example);
    }

    @Override
    public <E> List<E> findListByExample(Example<T> example, Class<E> entityClass) {
        example.selectProperties(selectProperties(entityClass));
        List<T> list = this.findListByExample(example);
        if(list == null ){
            return null ;
        }
        return Lists.transform(list, item -> {
            E e = instance(entityClass);
            BeanUtils.copyProperties(item,e);
            return e ;
        });
    }

    public Page<T> findPageByExample(Example<T> example, int pageNo, int pageSize) {
        Page<T> page = new Page<>(pageNo, pageSize);
        page.setRecords(mapper.selectPageByExample(page, example));
        return page;
    }

    @Override
    public <E> Page<E> findPageByExample(Example<T> example, int pageNo, int pageSize, final Class<E> entityClass) {
        example.selectProperties(selectProperties(entityClass));
        Page<T> page = this.findPageByExample(example, pageNo, pageSize);
        Page<E> page2 = new Page<>(page.getCurrent(),page.getSize());
        List<T> records = page.getRecords();
        if(records != null ){
            page2.setRecords(Lists.transform(records, item -> {
                E e = instance(entityClass);
               BeanUtils.copyProperties(item,e);
               return e ;
            }));
        }
        return page2;
    }

    private <E> E instance(Class<E> entityClass){
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new MybatisQuickException(e);
        }
    }

    private String[] selectProperties(Class<?> entityClass){
        Field[] fields = entityClass.getDeclaredFields();
        List<String> props = new ArrayList<>(fields.length);

        for(Field field : fields ){
            if( table.getColumn(field.getName()) != null ){
                props.add(field.getName());
            }
        }
        return props.toArray(new String[props.size()]);
    }

}
