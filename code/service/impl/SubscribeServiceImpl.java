package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.SubscribeMapper;
import com.zy.live.entity.Subscribe;
import com.zy.live.service.ISubscribeService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Subscribe 表数据服务层接口实现类
 *
 */
@Service
public class SubscribeServiceImpl extends SuperServiceImpl<SubscribeMapper, Subscribe,Long> implements ISubscribeService {


}