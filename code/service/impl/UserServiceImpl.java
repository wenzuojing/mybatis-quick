package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.UserMapper;
import com.zy.live.entity.User;
import com.zy.live.service.IUserService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User,Long> implements IUserService {


}