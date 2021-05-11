package com.czu.personalized_mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.entity.LeaseGoods;
import com.czu.personalized_mall.service.IGoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leaseDetails")
public class GoDetailsController {

    @Autowired
    private IGoDetailsService iGoDetailsService;

    @RequestMapping("/goDetials")
    public ModelAndView GoDetials(String goodsId){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("goods_id",goodsId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("leaseDetails",iGoDetailsService.getOne(wrapper));
        modelAndView.setViewName("commoditydetails");
        return modelAndView;
    }
}
