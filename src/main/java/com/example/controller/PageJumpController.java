package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用于页面的跳转
 * @Author: chenchong
 * @Date: 2021/1/5 20:06
 */
@Controller
@RequestMapping("/pageJump")
public class PageJumpController {
    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 搜索到的东西的页面
     * @return
     */
    @RequestMapping("/search")
    public String search(){
        return "search";
    }


    /**
     * 购物车页面
     *
     * @return
     */
    @RequestMapping("/car")
    public String car(){
        return "car";
    }

    /**
     * 心愿单页面
     * @return
     */
    @RequestMapping("collect")
    public String collect(){
        return "collect";
    }


    /**
     *个人的具体信息页面
     */
    @RequestMapping("/information")
    public String information(){
        return "information";
    }


    /**
     *
     */
    @RequestMapping("profile")
    public String profile(){
        return "profile";
    }

}
