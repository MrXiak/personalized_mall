package com.czu.personalized_mall.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.czu.personalized_mall.entity.Orders;
import com.czu.personalized_mall.entity.User;

import java.util.List;

public interface IOrderService extends IService<Orders> {
    public boolean save(Orders orders, User user, String address, String remark);
//    public List<OrderVO> findAllOrederVOByUserId(Integer id);
}
