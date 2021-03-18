package com.example.service;

import com.example.common.reponse.BaseResult;
import com.example.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-02-04
 */
public interface IUserService extends IService<User> {

    /**
     * 根据邮箱查询用户记录
     */
    User queryUserByEmail(String email);
    /**
     * 新增用户信息
     */
    void insertUser(User user);


}
