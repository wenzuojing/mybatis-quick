package com.zy.app.mapper;

import com.github.wens.mybatisplus.examples.Example;
import com.github.wens.mybatisplus.mapper.AutoMapper;
import com.github.wens.mybatisplus.plugins.Page;
import com.zy.app.entity.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TbUser 表数据库控制层接口
 */
public interface TbUserMapper extends AutoMapper<TbUser, String> {


    List<TbUser> findUsers(Page<TbUser> tbUserPage);

    List<TbUser> find(@Param("example") Example example);

}