package com.example.service.impl;

import com.example.common.constant.RegularExpressionConstant;
import com.example.common.exception.CommonException;
import com.example.common.exception.ExceptionEnum;
import com.example.common.reponse.BaseResult;
import com.example.model.entity.User;
import com.example.service.LoginService;
import com.example.util.RegularUtil;
import com.example.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/2/5 13:23
 */

@Service
@SuppressWarnings("all")
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JavaMailSender mailSender;


    /**
     * 注册用户
     */
    public BaseResult registeredUser(String email,String mailVerification,String password) {
        //邮箱不能为空
        if(StringUtils.isEmpty(email) || StringUtils.isBlank(email)){
            throw new CommonException(ExceptionEnum.PARAM_ERROR);
        }
        //验证邮箱格式是否正确
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }
        //验证码为空
        if(StringUtils.isBlank(mailVerification)){
            throw new CommonException(ExceptionEnum.VERIFICATION_CODE_IS_EMPTY);
        }else if(!mailVerification.equals(stringRedisTemplate.opsForValue().get(email))){
            //验证码错误
            throw new CommonException(ExceptionEnum.VERIFICATION_CODE_IS_ERROR);
        }
        //密码为空
        if(StringUtils.isBlank(password)){
            throw new CommonException(ExceptionEnum.PASSWORD_IS_EMPTY);
        }

        //邮箱唯一性
        User user = userService.queryUserByEmail(email);
        if(user != null){
            //用户已经存在
            throw new CommonException(ExceptionEnum.USER_ALREADY_EXISTS);
        }else{
            //可以注册
            User registeredUser = new User();
            registeredUser.setUserName(UUID.randomUUID().toString());
            registeredUser.setUserPassword(password);
            registeredUser.setEmail(email);
            userService.insertUser(registeredUser);
            return BaseResult.success();

        }

    }
    /**
     * 登录功能 - 密码登录
     */
    public BaseResult<String> loginByPassword(String email, String password) {
        //邮箱为空
        if(StringUtils.isBlank(email)){
            throw new CommonException(ExceptionEnum.MAILBOX_IS_EMPTY);
        }
        //邮箱格式
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }
        //密码为空
        if(StringUtils.isBlank(password)){
            throw new CommonException(ExceptionEnum.PASSWORD_IS_EMPTY);
        }
        //密码不正确
        User user = userService.queryUserByEmail(email);
        if(user == null){
            throw new CommonException(ExceptionEnum.USER_NOT_EXIST);
        }else if(!user.getUserPassword().equals(password)){
            throw new CommonException(ExceptionEnum.WRONG_PASSWORD);
        }
        //生成token
        String sign = TokenUtil.sign(String.valueOf(user.getId()),null);
        return BaseResult.success(sign);
    }

    /**
     * 登录功能 - 验证码登录
     */
    public BaseResult loginByVerification(String email, String mailVerification) {
        //邮箱为空
        if(StringUtils.isBlank(email)){
            throw new CommonException(ExceptionEnum.MAILBOX_IS_EMPTY);
        }
        //邮箱格式不正确
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }
        //用户不存在,邮箱还没有注册
        User user = userService.queryUserByEmail(email);
        if(user == null){
            throw new CommonException(ExceptionEnum.USER_NOT_EXIST);
        }
        //验证码为空
        if(StringUtils.isBlank(mailVerification)){
            throw new CommonException(ExceptionEnum.VERIFICATION_CODE_IS_EMPTY);
        }
        //验证码不正确
        if(!String.valueOf(stringRedisTemplate.opsForValue().get(email+".login")).equals(mailVerification)){
            throw new CommonException(ExceptionEnum.VERIFICATION_CODE_IS_ERROR);
        }
        return BaseResult.success();
    }



    /**
     * 发送邮箱验证码 - 为了注册
     */
    public void sendVerificationForRegister(String email){
        //邮箱不为空
        if(StringUtils.isBlank(email) || StringUtils.isEmpty(email)){
            throw new CommonException(ExceptionEnum.PARAM_ERROR);
        }
        //验证邮件的格式是否正确
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }
        //用户已经存在
        User user = userService.queryUserByEmail(email);
        if(user != null){
            throw new CommonException(ExceptionEnum.USER_ALREADY_EXISTS);
        }
        //随机生成验证码
        String randomCode = randomCode();
        //将验证码放进redis
        stringRedisTemplate.opsForValue().set(email+".register",randomCode);
        stringRedisTemplate.expire(email+".register",5, TimeUnit.MINUTES);
        //发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(RegularExpressionConstant.mailSubject);
        mailMessage.setFrom(RegularExpressionConstant.mailFromUser);
        mailMessage.setTo(email);

        ResourceBundle template = ResourceBundle.getBundle("system");
        MessageFormat formatter = new MessageFormat("");
        formatter.applyPattern(template.getString("registerAccount"));
        String[] args = {randomCode};
        String content = formatter.format(args);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
        BaseResult.success();

    }

    /***
     * 发送邮箱验证码 - 为了登录
     */
    public void sendVerificationForLogin(String email){
        //邮箱不为空
        if(StringUtils.isBlank(email) || StringUtils.isEmpty(email)){
            throw new CommonException(ExceptionEnum.PARAM_ERROR);
        }
        //验证邮件的格式是否正确
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }
        //用户不存在
        User user = userService.queryUserByEmail(email);
        if(user == null){
            throw new CommonException(ExceptionEnum.USER_NOT_EXIST);
        }
        //随机生成验证码
        String randomCode = randomCode();
        //将验证码放进redis
        stringRedisTemplate.opsForValue().set(email+".login",randomCode);
        stringRedisTemplate.expire(email+".login",5, TimeUnit.MINUTES);
        //发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(RegularExpressionConstant.mailSubject);
        mailMessage.setFrom(RegularExpressionConstant.mailFromUser);
        mailMessage.setTo(email);
        ResourceBundle template = ResourceBundle.getBundle("system");
        MessageFormat formatter = new MessageFormat("");
        formatter.applyPattern(template.getString("loginAccount"));
        String[] args = {randomCode};
        String content = formatter.format(args);
//        String loginAccount = messageSource.getMessage("loginAccount", null, null);
//        String format = messageFormat.format(loginAccount, randomCode);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
        BaseResult.success();

    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public BaseResult checkEmail(String email) {
        //邮箱不能为空
        if(StringUtils.isEmpty(email) || StringUtils.isBlank(email)){
            throw new CommonException(ExceptionEnum.PARAM_ERROR);
        }
        //验证邮箱格式是否正确
        boolean flag = RegularUtil.isRegular(RegularExpressionConstant.emailRegular, email);
        if(!flag){
            //邮箱格式不对
            throw new CommonException(ExceptionEnum.MAILBOX_FORMAT_ERROR);
        }

        User user = userService.queryUserByEmail(email);
        if(user != null){
            //用户已经存在
            throw new CommonException(ExceptionEnum.USER_ALREADY_EXISTS);
        }else{
            return BaseResult.success();
        }
    }

    /**
     * 随机生成验证码
     */
    public static String randomCode(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


}
