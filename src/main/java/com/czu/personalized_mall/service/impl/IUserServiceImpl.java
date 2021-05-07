package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.mapper.UserMapper;
import com.czu.personalized_mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(String account, String password) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",account);
        if (userMapper.selectOne(wrapper)==null){
            User user=new User();
            user.setUserId(account);
            user.setPassword(password);
            userMapper.insert(user);
        }
    }
//    @Resource
//    private UserMapper UserMapper;
//
//    public User confirmUser(String account) {
//        return UserMapper.confirmLogin(account);
//    }
//
//    public void adduser(String account, String password) {
//        UserMapper.insertAccount(account, password);
//    }
}
