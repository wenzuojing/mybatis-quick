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
package com.github.wens.framework.service;

import com.github.wens.mybatisplus.examples.Example;
import com.github.wens.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 * 抽象 Service
 * </p>
 *
 * @author hubin
 * @Date 2016-03-23
 */
public interface ISuperService<T, I> {

    /**
     * <p>
     * 根据条件查询总数
     * </p>
     *
     * @param example
     * @return
     */
    long countByExample(Example<T> example);

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    boolean insert(T entity);

    /**
     * <p>
     * 插入一条记录（选择字段， null 字段不插入）
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    boolean insertSelective(T entity);


    /**
     * <p>
     * 插入（批量），该方法不适合 Oracle
     * </p>
     *
     * @param entityList 实体对象列表
     * @return int
     */
    boolean insertBatch(List<T> entityList);


    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    boolean deleteById(I id);


    /**
     * <p>
     * 根据 example 条件，删除记录
     * </p>
     *
     * @param example
     * @return
     */
    boolean deleteByExample(Example<T> example);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    boolean deleteBatchIds(List<I> idList);


    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    boolean updateById(T entity);


    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     */
    boolean updateSelectiveById(T entity);


    /**
     * <p>
     * 根据 whereEntity 条件，选择更新记录
     * </p>
     *
     * @param entity 实体对象
     * @return example
     * 实体查询条件
     */
    boolean updateSelectiveByExample(T entity, Example<T> example);


    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T findById(I id);


    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return List<T>
     */
    List<T> findByIds(List<I> idList);


    /**
     * <p>
     * 根据 entity 条件，查询一条记录
     * </p>
     *
     * @param example
     * @return T
     */
    T findOneByExample(Example<T> example);


    /**
     * <p>
     * 根据 entity 条件，查询全部记录
     * </p>
     *
     * @param example
     * @return List<T>
     */
    List<T> findListByExample(Example<T> example);

    /**
     * <p>
     * 根据 entity 条件，查询全部记录
     * </p>
     *
     * @param
     * @param example
     * @param pageNo
     * @param pageSize
     * @return List<T>
     */
    Page<T> findPageByExample(Example<T> example, int pageNo, int pageSize);

}
