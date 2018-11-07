package com.github.wens.mybatis.support.service;

import com.github.wens.mybatis.example.Example;
import com.github.wens.mybatis.support.Page;

import java.util.List;

/**
 * @author wens
 * @Date 2018-10-10
 */
public interface ISuperService<T, I> {


    long countByExample(Example<T> example);


    boolean insert(T entity);


    boolean insertSelective(T entity);


    boolean insertBatch(List<T> entityList);


    boolean deleteById(I id);


    boolean deleteByExample(Example<T> example);


    boolean deleteBatchIds(List<I> idList);


    boolean updateById(T entity);


    boolean updateSelectiveById(T entity);


    boolean updateSelectiveByExample(T entity, Example<T> example);


    T findById(I id);


    <E> E findById(I id, Class<E> entityClass);


    List<T> findByIds(List<I> ids);


    <E> List<E> findByIds(List<I> idList, Class<E> entityClass);


    T findOneByExample(Example<T> example);


    <E> E findOneByExample(Example<T> example, Class<E> entityClass);


    List<T> findListByExample(Example<T> example);


    <E> List<E> findListByExample(Example<T> example, Class<E> entityClass);


    Page<T> findPageByExample(Example<T> example, int pageNo, int pageSize);


    <E> Page<E> findPageByExample(Example<T> example, int pageNo, int pageSize, Class<E> entityClass);

}
