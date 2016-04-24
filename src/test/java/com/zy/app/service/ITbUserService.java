package com.zy.app.service;

import com.github.wens.framework.service.ISuperService;
import com.github.wens.mybatisplus.plugins.Page;
import com.zy.app.entity.TbUser;

/**
 * TbUser 表数据服务层接口
 */
public interface ITbUserService extends ISuperService<TbUser, String> {


    Page<TbUser> findUsers();
}