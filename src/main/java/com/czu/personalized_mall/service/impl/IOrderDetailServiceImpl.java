package com.czu.personalized_mall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.OrderDetail;
import com.czu.personalized_mall.entity.Orders;
import com.czu.personalized_mall.mapper.OrderDetailMapper;
import com.czu.personalized_mall.mapper.OrderMapper;
import com.czu.personalized_mall.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDetail> list() {
        OrderDetail orderDetail = new OrderDetail();
        Orders orders = orderMapper.selectById(orderDetail.getOrderId());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orders.getId());
        List list = orderDetailMapper.selectList(wrapper);
        return list;
    }
}
