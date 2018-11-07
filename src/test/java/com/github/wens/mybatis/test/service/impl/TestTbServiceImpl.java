package com.github.wens.mybatis.test.service.impl;

import com.github.wens.mybatis.support.service.impl.SuperServiceImpl;
import com.github.wens.mybatis.test.entity.TestTb;
import com.github.wens.mybatis.test.mapper.TestTbMapper;
import com.github.wens.mybatis.test.service.ITestTbService;
import org.springframework.stereotype.Service;

/**
 * TestTb 表数据服务层接口实现类
 */
@Service
public class TestTbServiceImpl extends SuperServiceImpl<TestTbMapper, TestTb, Long> implements ITestTbService {


}