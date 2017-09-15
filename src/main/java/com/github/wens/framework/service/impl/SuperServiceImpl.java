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
package com.github.wens.framework.service.impl;

import com.github.wens.framework.service.ISuperService;
import com.github.wens.mybatisplus.examples.Example;
import com.github.wens.mybatisplus.mapper.AutoMapper;
import com.github.wens.mybatisplus.plugins.Page;
import com.github.wens.mybatisplus.toolkit.TableInfo;
import com.github.wens.mybatisplus.toolkit.TableInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 抽象 Service 实现类（ 泛型：M 是 mapper 对象， T 是实体 ）
 * </p>
 *
 * @author hubin
 * @Date 2016-03-23
 */
public class SuperServiceImpl<M extends AutoMapper<T, I>, T, I> implements ISuperService<T, I> {

    protected Class<T> entityClass;

    protected TableInfo table;

    @Autowired
    protected M autoMapper;

    public   SuperServiceImpl(){
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(SuperServiceImpl.class) ){
            Type[] parameters = ((ParameterizedType) type).getActualTypeArguments();
            entityClass  = (Class<T>) parameters[1];
            table = TableInfoHelper.getTableInfo(entityClass);
        }
    }


    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(int result) {
        return (result >= 1) ? true : false;
    }


    @Override
    public long countByExample(Example<T> example) {
        return autoMapper.countByExample(example);
    }

    public boolean insert(T entity) {
        return retBool(autoMapper.insert(entity));
    }


    public boolean insertSelective(T entity) {
        return retBool(autoMapper.insertSelective(entity));
    }


    public boolean insertBatch(List<T> entityList) {
        return retBool(autoMapper.insertBatch(entityList));
    }


    public boolean deleteById(I id) {
        return retBool(autoMapper.deleteById(id));
    }


    public boolean deleteByExample(Example<T> example) {
        return retBool(autoMapper.deleteByExample(example));
    }

    public boolean deleteBatchIds(List<I> idList) {
        return retBool(autoMapper.deleteBatchIds(idList));
    }


    public boolean updateById(T entity) {
        return retBool(autoMapper.updateById(entity));
    }


    public boolean updateSelectiveById(T entity) {
        return retBool(autoMapper.updateSelectiveById(entity));
    }


    public boolean updateSelectiveByExample(T entity, Example<T> example) {
        return retBool(autoMapper.updateSelectiveByExample(entity, example));
    }


    public T findById(I id) {
        return autoMapper.selectById(id);
    }

    @Override
    public T findById(I id, String... selectProps) {
        if(selectProps == null || selectProps.length == 0 ){
            return findById(id);
        }
        Example<T> example = new Example<T>(this.entityClass);
        example.selectProperties(selectProps);
        example.createCriteria().andEqualTo(table.getKeyProperty(),id );
        return findOneByExample(example);
    }

    public List<T> findByIds(List<I> idList) {
        if(idList == null || idList.isEmpty() ){
            return Collections.EMPTY_LIST ;
        }
        return autoMapper.selectByIds(idList);
    }

    @Override
    public List<T> findByIds(List<I> idList, String... selectProps) {
        if(selectProps == null || selectProps.length == 0 ){
            return findByIds(idList);
        }
        Example<T> example = new Example<T>(this.entityClass);
        example.selectProperties(selectProps);
        example.createCriteria().andIn(table.getKeyProperty(),idList );
        return findListByExample(example);
    }

    @Override
    public T findOneByExample(Example<T> example) {
        return autoMapper.selectOneByExample(example);
    }


    public List<T> findListByExample(Example<T> example) {
        return autoMapper.selectListByExample(example);
    }


    public Page<T> findPageByExample(Example<T> example, int pageNo, int pageSize) {
        Page<T> page = new Page<>(pageNo, pageSize);
        page.setRecords(autoMapper.selectPageByExample(page, example));
        return page;
    }


}
