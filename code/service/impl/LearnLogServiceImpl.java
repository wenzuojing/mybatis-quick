package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.LearnLogMapper;
import com.zy.live.entity.LearnLog;
import com.zy.live.service.ILearnLogService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * LearnLog 表数据服务层接口实现类
 *
 */
@Service
public class LearnLogServiceImpl extends SuperServiceImpl<LearnLogMapper, LearnLog,Long> implements ILearnLogService {


}