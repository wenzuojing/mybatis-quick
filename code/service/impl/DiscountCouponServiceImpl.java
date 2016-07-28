package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.DiscountCouponMapper;
import com.zy.live.entity.DiscountCoupon;
import com.zy.live.service.IDiscountCouponService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * DiscountCoupon 表数据服务层接口实现类
 *
 */
@Service
public class DiscountCouponServiceImpl extends SuperServiceImpl<DiscountCouponMapper, DiscountCoupon,Long> implements IDiscountCouponService {


}