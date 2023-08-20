package com.hzp.dao;

import com.hzp.pojo.User;

public interface UserDao {
    /**
     *通过用户名查找
     * @param
     * @return
     */
    User queryUserByUsername(String username);
    /**
     * 通过用户名和密码查找
     * @param username
     * @param password
     * @return
     */
    User queryUserByUsernameAndPassword(String username,String password);

    boolean saveUser(User user);
}
