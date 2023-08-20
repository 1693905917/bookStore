package com.hzp.service.impl;

import com.hzp.dao.UserDao;
import com.hzp.dao.impl.UserDaoImpl;
import com.hzp.pojo.User;
import com.hzp.service.UserService;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-21 21:31
 */
public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username).getUsername()==null){
            return false;
        }
        return true;
    }
}
