package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.SmsMapper;
import com.zy.live.entity.Sms;
import com.zy.live.service.ISmsService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Sms 表数据服务层接口实现类
 *
 */
@Service
public class SmsServiceImpl extends SuperServiceImpl<SmsMapper, Sms,Long> implements ISmsService {


}