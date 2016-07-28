package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.SignInfoMapper;
import com.zy.live.entity.SignInfo;
import com.zy.live.service.ISignInfoService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * SignInfo 表数据服务层接口实现类
 *
 */
@Service
public class SignInfoServiceImpl extends SuperServiceImpl<SignInfoMapper, SignInfo,Long> implements ISignInfoService {


}