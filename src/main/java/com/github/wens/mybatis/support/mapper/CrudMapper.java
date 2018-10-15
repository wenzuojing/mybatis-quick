package com.github.wens.mybatis.support.mapper;

import com.github.wens.mybatis.examples.Example;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public interface CrudMapper<T, I> {


    long countByExample(@Param("ex") Example<T> example);


    int insert(T entity);


    int insertSelective(T entity);


    int insertBatch(List<T> entityList);


    int deleteById(I id);


    int deleteByExample(@Param("ex") Example<T> example);


    int deleteByIds(List<I> ids);


    int updateById(@Param("et") T entity);


    int updateSelectiveById(@Param("et") T entity);


    int updateSelectiveByExample(@Param("et") T entity, @Param("ex") Example<T> example);


    T selectById(@Param("id") I id);


    List<T> selectByIds(@Param("ids") List<I> ids);


    T selectOneByExample(@Param("ex") Example<T> example);


    List<T> selectListByExample(@Param("ex") Example<T> example);


    List<T> selectPageByExample(RowBounds rowBounds, @Param("ex") Example<T> example);

}
