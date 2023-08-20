package com.hzp.service;

import com.hzp.pojo.User;

public interface UserService {
    /**
     * 注册用户
     */
    void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 检测  用户名是否可用
     *
     * @param username
     * @return  false:查询用户不存在  true:查询用户存在
     */
    boolean existUsername(String username);
}
