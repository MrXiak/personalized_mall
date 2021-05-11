package com.czu.personalized_mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.entity.LeaseGoods;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.ILeaseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/lease")
public class LeaseGoodsController {
    @Autowired
    private ILeaseGoodsService leaseGoodsService;

    @RequestMapping("/commodityindex")
    public ModelAndView showSecond(String type){
        ModelAndView modelAndView = new ModelAndView();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("goods_first_kind",type);
        modelAndView.addObject("leaseGoods",leaseGoodsService.list(wrapper));
        switch (type) {
            case "婚庆":
                modelAndView.setViewName("commodityindex/weddingcommodityindex");
                break;
            case "交通工具":
                modelAndView.setViewName("commodityindex/carcommodityindex");
                break;
            case "办公":
                modelAndView.setViewName("commodityindex/officecommodityindex");
                break;
            default:
                modelAndView.setViewName("commodityindex/homecommodityindex");
                break;
        }
        return modelAndView;
    }
}
