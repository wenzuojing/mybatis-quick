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
package com.github.wens.mybatisplus.mapper;

import com.github.wens.mybatisplus.examples.Example;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * </p>
 * <p>
 * 这个 Mapper 支持 id 泛型
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public interface BaseMapper<T, I> {

    /**
     * <p>
     * 根据条件查询总数
     * </p>
     *
     * @param example
     * @return
     */
    long countByExample(@Param("ex") Example<T> example);

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    int insert(T entity);

    /**
     * <p>
     * 插入一条记录（选择字段， null 字段不插入）
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    int insertSelective(T entity);


    /**
     * <p>
     * 插入（批量），该方法不适合 Oracle
     * </p>
     *
     * @param entityList 实体对象列表
     * @return int
     */
    int insertBatch(List<T> entityList);


    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    int deleteById(I id);


    /**
     * <p>
     * 根据 example 条件，删除记录
     * </p>
     *
     * @param example
     * @return
     */
    int deleteByExample(@Param("ex") Example<T> example);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    int deleteBatchIds(List<I> idList);


    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    int updateById(@Param("et") T entity);


    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     */
    int updateSelectiveById(@Param("et") T entity);


    /**
     * <p>
     * 根据 whereEntity 条件，选择更新记录
     * </p>
     *
     * @param entity 实体对象
     * @return example
     * 实体查询条件
     */
    int updateSelectiveByExample(@Param("et") T entity, @Param("ex") Example<T> example);


    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T selectById(I id);


    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return List<T>
     */
    List<T> selectByIds(List<I> idList);


    /**
     * <p>
     * 根据 entity 条件，查询一条记录
     * </p>
     *
     * @param example
     * @return T
     */
    T selectOneByExample(@Param("ex") Example<T> example);


    /**
     * <p>
     * 根据 entity 条件，查询全部记录
     * </p>
     *
     * @param example
     * @return List<T>
     */
    List<T> selectListByExample(@Param("ex") Example<T> example);

    /**
     * <p>
     * 根据 entity 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param rowBounds
     * @param example
     * @return List<T>
     */
    List<T> selectPageByExample(RowBounds rowBounds, @Param("ex") Example<T> example);

}
