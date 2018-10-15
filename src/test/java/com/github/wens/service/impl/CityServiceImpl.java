package com.github.wens.service.impl;

import org.springframework.stereotype.Service;

import com.github.wens.mapper.CityMapper;
import com.github.wens.entity.City;
import com.github.wens.service.ICityService;
import com.github.wens.mybatis.support.service.impl.SuperServiceImpl;

/**
 *
 * City 表数据服务层接口实现类
 *
 */
@Service
public class CityServiceImpl extends SuperServiceImpl<CityMapper, City,Long> implements ICityService {


}