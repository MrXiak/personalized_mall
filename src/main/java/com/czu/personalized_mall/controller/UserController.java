package com.czu.personalized_mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czu.personalized_mall.service.IUserService;
import com.czu.personalized_mall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
//    @Autowired
//    private CartService cartService;

    //    @PostMapping("/register")
//    public String register(User user, Model model){
//        boolean result = false;
//        try {
////            if(user.getGenderCode() == 1){
////                user.setGender(GenderEnum.MAN);
////            }
////            if(user.getGenderCode() == 0){
////                user.setGender(GenderEnum.WOMAN);
////            }
//            result = iUserService.save(user);
//        } catch (Exception e) {
//            model.addAttribute("error",user.getUserId()+"已存在！请重新输入！");
//            return "register";
//        }
//        if(result) return "userlogin";
//        return "register";
//    }
    @RequestMapping("/confirmRegister")
    public int isRegistre(String account, String password) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",account);
        User user = iUserService.getOne(wrapper);
        int m;
        if (user == null) {
            iUserService.save(account,password);
            m = 1;
        } else {
            m = 0;
        }
        return m;
    }




    @RequestMapping("/confirmLogin")
    public Map confirmLogin(String account, String password, HttpSession session){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",account);
        User user=iUserService.getOne(wrapper);
        int m;
        Map map =new HashMap();
        if (user==null){
            m=0;
        }else {
            if (user.getPassword().equals(password)){
                session.setAttribute("user", user);
                m=1;
            }else {
                m=2;
            }
        }
        map.put("r",m);
        return map;
    }


    /**
     * 退出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("/user/UserLogin");
    }

    /**
     * 用户信息
     */
//    @GetMapping("/userInfo")
//    public ModelAndView userInfo(HttpSession session){
//        User user = (User) session.getAttribute("user");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("userInfo");
//        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
//        return modelAndView;
//    }
}

