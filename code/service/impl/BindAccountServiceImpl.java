package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.BindAccountMapper;
import com.zy.live.entity.BindAccount;
import com.zy.live.service.IBindAccountService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * BindAccount 表数据服务层接口实现类
 *
 */
@Service
public class BindAccountServiceImpl extends SuperServiceImpl<BindAccountMapper, BindAccount,Long> implements IBindAccountService {


}