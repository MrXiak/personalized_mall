package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.Lease;
import com.czu.personalized_mall.mapper.LeaseMapper;
import com.czu.personalized_mall.service.ILeaseService;
import org.springframework.stereotype.Service;

@Service
public class ILeaseServiceImpl  extends ServiceImpl<LeaseMapper, Lease> implements ILeaseService {
}
