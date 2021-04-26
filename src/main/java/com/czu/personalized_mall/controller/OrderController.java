package com.czu.personalized_mall.controller;


import com.czu.personalized_mall.entity.Orders;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.ICartService;
import com.czu.personalized_mall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartService cartService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(Orders orders, HttpSession session, String address, String remark){
        User user = (User) session.getAttribute("user");
        orderService.save(orders,user,address,remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }
//
//    @GetMapping("/list")
//    public ModelAndView list(HttpSession session){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("orderList");
//        User user = (User) session.getAttribute("user");
//        modelAndView.addObject("list",orderService.findAllOrederVOByUserId(user.getId()));
//        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
//        return modelAndView;
//    }
}

