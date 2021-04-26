package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.UserAddress;
import com.czu.personalized_mall.mapper.UserAddressMapper;
import com.czu.personalized_mall.service.IUserAddressService;
import org.springframework.stereotype.Service;

@Service
public class IUserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

}
