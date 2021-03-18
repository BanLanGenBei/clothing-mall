package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.constant.RegularExpressionConstant;
import com.example.common.exception.CommonException;
import com.example.common.exception.ExceptionEnum;
import com.example.common.reponse.BaseResult;
import com.example.model.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.RegularUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-02-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    @Override
    public User queryUserByEmail(String email) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email));
    }

    /**
     * 新增用户信息
     * @param user
     */
    @Override
    public void insertUser(User user) {
        this.baseMapper.insert(user);
    }


}
