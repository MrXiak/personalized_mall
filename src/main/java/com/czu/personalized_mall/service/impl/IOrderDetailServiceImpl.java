package com.czu.personalized_mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.OrderDetail;
import com.czu.personalized_mall.mapper.OrderDetailMapper;
import com.czu.personalized_mall.service.IOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class IOrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
