package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.*;
import com.czu.personalized_mall.mapper.*;
import com.czu.personalized_mall.service.IOrderService;
import com.czu.personalized_mall.vo.OrderDetailVO;
import com.czu.personalized_mall.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Orders orders, User user, String address, String remark) {
        //判断是新地址还是老地址
        if(orders.getUserAddress().equals("newAddress")){
            //存入数据库
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            userAddress.setUserId(user.getId());

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("isdefault",1);
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            oldDefault.setIsdefault(0);
            userAddressMapper.updateById(oldDefault);
            userAddressMapper.insert(userAddress);
            orders.setUserAddress(address);
        }
        //存储orders
        orders.setUserId(user.getId());
        orders.setLoginName(user.getUserName());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        orderMapper.insert(orders);

        //存储ordersdetail
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("user_id",user.getId());
        cartMapper.delete(wrapper1);
        return true;
    }
//
//    @Override
//    public List<OrderVO> findAllOrederVOByUserId(Integer id) {
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("user_id",id);
//        List<Orders> ordersList = orderMapper.selectList(wrapper);
//        //VO转换
////        List<OrderVO> orderVOList = new ArrayList<>();
////        for (Orders orders : ordersList) {
////            OrderVO orderVO = new OrderVO();
////            BeanUtils.copyProperties(orders,orderVO);
////            orderVOList.add(orderVO);
////        }
//
//        List<OrderVO> orderVOList = ordersList.stream()
//                .map(e -> new OrderVO(
//                        e.getId(),
//                        e.getLoginName(),
//                        e.getSerialnumber(),
//                        e.getUserAddress(),
//                        e.getCost()
//                )).collect(Collectors.toList());
//        //封装OrderDetail
//        for (OrderVO orderVO : orderVOList) {
//            QueryWrapper wrapper1 = new QueryWrapper();
//            wrapper1.eq("order_id",orderVO.getId());
//            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(wrapper1);
//            List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
//            for (OrderDetail orderDetail : orderDetailList) {
//                OrderDetailVO orderDetailVO = new OrderDetailVO();
//                Goods goods = goodsMapper.selectById(orderDetail.getProductId());
//                BeanUtils.copyProperties(goods,orderDetailVO);
//                BeanUtils.copyProperties(orderDetail,orderDetailVO);
//                orderDetailVOList.add(orderDetailVO);
//            }
//            orderVO.setOrderDetailVOList(orderDetailVOList);
//        }
//        return orderVOList;
//    }
}
