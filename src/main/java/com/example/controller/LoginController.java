package com.example.controller;


import com.example.common.reponse.BaseResult;
import com.example.service.impl.LoginServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/2/4 14:46
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;
    /**
     * 注册用户
     * @param email
     * @return
     */
    @GetMapping("/registeredUser")
    public BaseResult registeredUser(@RequestParam("email") String email,
                                     @RequestParam("mailVerification") String mailVerification,
                                     @RequestParam("password") String password
                                     ){
        return loginService.registeredUser(email, mailVerification, password);

    }
    /**
     * 验证邮箱
     */
    @GetMapping("/checkEmail")
    public BaseResult checkEmail(@RequestParam("email") String email){
        return loginService.checkEmail(email);
    }

    /**
     * 发送验证码为了注册
     * @param email
     * @return
     */
    @PostMapping("/sendVerificationForRegister")
    public void sendVerificationForRegister(@RequestParam("email") String email){
        loginService.sendVerificationForRegister(email);

    }
    /**
     * 发送验证码为了登录
     */
    @PostMapping("/sendVerificationForLogin")
    public void sendVerificationForLogin(@RequestParam("email") String email){
        loginService.sendVerificationForLogin(email);
    }

    /**
     * 密码登录
     */
    @PostMapping("/loginByPassword")
    public BaseResult<String> loginByPassword(@RequestParam("email") String email,
                            @RequestParam("password") String password){
        return loginService.loginByPassword(email,password);
    }

    /**
     * 验证码登录
     */
    @PostMapping("/loginByVerification")
    public BaseResult loginByVerification(@RequestParam("email") String email,
                                          @RequestParam("mailVerification") String mailVerification ){
        return loginService.loginByVerification(email,mailVerification);
    }




}
