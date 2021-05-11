package com.czu.personalized_mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.entity.Lease;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.IGoDetailsService;
import com.czu.personalized_mall.service.ILeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/outLease")
public class LeaseController {
    @Autowired
    private ILeaseService iLeaseService;
    @Autowired
    private IGoDetailsService iGoDetailsService;
    @RequestMapping("/insertLease")
    public ModelAndView insertLease(String goodsId,String begintime,HttpSession session){
        Lease lease=new Lease();
        lease.setGoodsId(goodsId);
        lease.setLeaseTime(begintime);
        Date date=new Date();
        String d="OR"+date.getTime();
        lease.setLeaseId(d);
        User user = (User)session.getAttribute("user");
        lease.setUserId(user.getUserId());
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("goods_id",goodsId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("leaseDetails",iGoDetailsService.getOne(wrapper));
        iLeaseService.save(lease);
        modelAndView.addObject("leaseInfo",lease);
        modelAndView.setViewName("UserLease");
        return modelAndView;
    }
}
