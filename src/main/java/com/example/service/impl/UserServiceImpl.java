package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.model.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-02-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
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

    /**
     * 根据用户的id获取用户
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        User user = this.baseMapper.selectById(userId);
        return user;
    }


}
