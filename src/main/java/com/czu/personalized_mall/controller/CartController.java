package com.czu.personalized_mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.entity.Cart;
import com.czu.personalized_mall.entity.User;
import com.czu.personalized_mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;
//    @Autowired
//    private UserAddressService userAddressService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ) {
        Cart cart = new Cart();
        cart.setGoodsId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price * quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if (iCartService.save(cart)) {//修改库存
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e) {
            return "redirect:/productCategory/list";
        }
        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("cartList",iCartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
//
//    @GetMapping("/deleteById/{id}")
//    public String deleteById(@PathVariable("id") Integer id){
//        cartService.removeById(id);
//        return "redirect:/cart/findAllCart";
//    }
//
//    @GetMapping("/settlement2")
//    public ModelAndView settlement2(HttpSession session){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("settlement2");
//        User user = (User)session.getAttribute("user");
//        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("user_id",user.getId());
//        modelAndView.addObject("addressList",userAddressService.list(wrapper));
//        return modelAndView;
//    }
//
//    @PostMapping("/update/{id}/{quantity}/{cost}")
//    @ResponseBody
//    public String updateCart(
//            @PathVariable("id") Integer id,
//            @PathVariable("quantity") Integer quantity,
//            @PathVariable("cost") Float cost
//    ){
//        Cart cart = cartService.getById(id);
//        cart.setQuantity(quantity);
//        cart.setCost(cost);
//        if(cartService.updateById(cart)){
//            return "success";
//        }else{
//            return "fail";
//        }
//    }
}

