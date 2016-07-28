package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.LectureMapper;
import com.zy.live.entity.Lecture;
import com.zy.live.service.ILectureService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Lecture 表数据服务层接口实现类
 *
 */
@Service
public class LectureServiceImpl extends SuperServiceImpl<LectureMapper, Lecture,Long> implements ILectureService {


}