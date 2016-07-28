package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.CoursewareMapper;
import com.zy.live.entity.Courseware;
import com.zy.live.service.ICoursewareService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Courseware 表数据服务层接口实现类
 *
 */
@Service
public class CoursewareServiceImpl extends SuperServiceImpl<CoursewareMapper, Courseware,Long> implements ICoursewareService {


}