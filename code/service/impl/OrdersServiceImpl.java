package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.OrdersMapper;
import com.zy.live.entity.Orders;
import com.zy.live.service.IOrdersService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Orders 表数据服务层接口实现类
 *
 */
@Service
public class OrdersServiceImpl extends SuperServiceImpl<OrdersMapper, Orders,Long> implements IOrdersService {


}