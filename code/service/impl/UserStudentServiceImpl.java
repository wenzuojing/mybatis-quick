package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.UserStudentMapper;
import com.zy.live.entity.UserStudent;
import com.zy.live.service.IUserStudentService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * UserStudent 表数据服务层接口实现类
 *
 */
@Service
public class UserStudentServiceImpl extends SuperServiceImpl<UserStudentMapper, UserStudent,Long> implements IUserStudentService {


}