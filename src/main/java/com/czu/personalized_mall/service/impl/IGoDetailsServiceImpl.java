package com.czu.personalized_mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.LeaseGoods;
import com.czu.personalized_mall.mapper.GoDetailsMapper;
import com.czu.personalized_mall.service.IGoDetailsService;
import org.springframework.stereotype.Service;

@Service
public class IGoDetailsServiceImpl extends ServiceImpl<GoDetailsMapper, LeaseGoods> implements IGoDetailsService {
}
