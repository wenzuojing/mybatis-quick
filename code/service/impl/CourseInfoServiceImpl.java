package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.CourseInfoMapper;
import com.zy.live.entity.CourseInfo;
import com.zy.live.service.ICourseInfoService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * CourseInfo 表数据服务层接口实现类
 *
 */
@Service
public class CourseInfoServiceImpl extends SuperServiceImpl<CourseInfoMapper, CourseInfo,Long> implements ICourseInfoService {


}