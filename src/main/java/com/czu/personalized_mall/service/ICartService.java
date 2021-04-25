package com.czu.personalized_mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czu.personalized_mall.entity.Cart;
import com.czu.personalized_mall.entity.GoodsCategory;
import com.czu.personalized_mall.vo.CartVO;

import java.util.List;

public interface ICartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);

}
