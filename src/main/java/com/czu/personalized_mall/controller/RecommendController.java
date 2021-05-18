package com.czu.personalized_mall.controller;

import com.czu.personalized_mall.service.IOrderDetailService;
import com.czu.personalized_mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RecommendController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderDetailService iOrderDetailService;
    @RequestMapping("/recommend")
    public void recommend(HttpSession session) {
    }
}
