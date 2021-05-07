package com.czu.personalized_mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czu.personalized_mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

public interface UserMapper extends BaseMapper<User> {
//    User confirmLogin(@Param("account") String account);
//    void insertAccount(@Param("account") String account, @Param("password") String password);
}
