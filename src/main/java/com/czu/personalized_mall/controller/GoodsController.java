package com.czu.personalized_mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.ICartService;
import com.czu.personalized_mall.service.IGoodsCategoryService;
import com.czu.personalized_mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsCategoryService iGoodsCategoryService;
    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") String type,@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("GoodsList");
//        modelAndView.addObject("list",iGoodsCategoryService.getAllGoodsCategoryVO());
        modelAndView.addObject("goodsList",iGoodsService.findByCategoryId(type,id));
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("GoodsDetail");
        modelAndView.addObject("goods",iGoodsService.getById(id));
        modelAndView.addObject("list",iGoodsCategoryService.getAllGoodsCategoryVO());
//        User user = (User)session.getAttribute("user");
//        if(user == null){
//            modelAndView.addObject("cartList",new ArrayList<>());
//        }else{
//            modelAndView.addObject("cartList",iCartService.findAllCartVOByUserId(user.getId()));
//        }
        return modelAndView;
    }

    @PostMapping("/findByKey")
    public ModelAndView findByKey(String keyWord,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("GoodsList");
        //根据关键字查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name",keyWord);
        modelAndView.addObject("goodsList",iGoodsService.list(wrapper));
//        modelAndView.addObject("list",iGoodsCategoryService.getAllGoodsCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",iCartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
}
