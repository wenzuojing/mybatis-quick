package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.UserTeacherMapper;
import com.zy.live.entity.UserTeacher;
import com.zy.live.service.IUserTeacherService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * UserTeacher 表数据服务层接口实现类
 *
 */
@Service
public class UserTeacherServiceImpl extends SuperServiceImpl<UserTeacherMapper, UserTeacher,Long> implements IUserTeacherService {


}