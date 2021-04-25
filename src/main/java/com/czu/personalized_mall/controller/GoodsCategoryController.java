package com.czu.personalized_mall.controller;

import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.ICartService;
import com.czu.personalized_mall.service.IGoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {
    @Autowired
    private IGoodsCategoryService iGoodsCategoryService;
    @Autowired
    private ICartService iCartService;
    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("list",iGoodsCategoryService.getAllGoodsCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",iCartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
}
