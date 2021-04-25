package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.Cart;
import com.czu.personalized_mall.entity.Goods;
import com.czu.personalized_mall.enums.ResultEnum;
import com.czu.personalized_mall.exception.MallException;
import com.czu.personalized_mall.mapper.CartMapper;
import com.czu.personalized_mall.mapper.GoodsMapper;
import com.czu.personalized_mall.service.ICartService;
import com.czu.personalized_mall.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ICartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService{
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Cart entity) {
        //扣库存
        Goods goods = goodsMapper.selectById(entity.getGoodsId());
        Integer stock = goods.getStock() - entity.getQuantity();
        if(stock < 0){
            log.error("【添加购物车】库存不足！stock={}",stock);
            throw new MallException(ResultEnum.STOCK_ERROR);
        }
        goods.setStock(stock);
        goodsMapper.updateById(goods);
        if(cartMapper.insert(entity) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<CartVO> findAllCartVOByUserId(Integer id) {
        List<CartVO> cartVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            BeanUtils.copyProperties(goods,cartVO);
            BeanUtils.copyProperties(cart,cartVO);
            cartVO.setProductId(goods.getId());
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
}
