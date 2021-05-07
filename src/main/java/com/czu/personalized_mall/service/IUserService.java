package com.czu.personalized_mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czu.personalized_mall.entity.Orders;
import com.czu.personalized_mall.entity.User;

public interface IUserService extends IService<User> {
//    User confirmUser(String account);
//    void adduser(String account, String password);

    public void save(String account, String password);
}
