package com.github.wens.service.impl;

import org.springframework.stereotype.Service;

import com.github.wens.mapper.TestTbMapper;
import com.github.wens.entity.TestTb;
import com.github.wens.service.ITestTbService;
import com.github.wens.mybatis.support.service.impl.SuperServiceImpl;

/**
 *
 * TestTb 表数据服务层接口实现类
 *
 */
@Service
public class TestTbServiceImpl extends SuperServiceImpl<TestTbMapper, TestTb,Long> implements ITestTbService {


}