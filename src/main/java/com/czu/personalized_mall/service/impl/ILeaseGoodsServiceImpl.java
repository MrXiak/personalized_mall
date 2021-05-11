package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.LeaseGoods;
import com.czu.personalized_mall.mapper.LeaseGoodsMapper;
import com.czu.personalized_mall.service.ILeaseGoodsService;
import org.springframework.stereotype.Service;

@Service
public class ILeaseGoodsServiceImpl extends ServiceImpl<LeaseGoodsMapper, LeaseGoods> implements ILeaseGoodsService {
}
