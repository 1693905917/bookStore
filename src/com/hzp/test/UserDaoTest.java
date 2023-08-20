package com.hzp.test;

import com.hzp.dao.UserDao;
import com.hzp.dao.impl.UserDaoImpl;
import com.hzp.pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest extends Test {

    @org.junit.Test
    public void queryUserByUsername() {
    }

    @org.junit.Test
    public void queryUserByUsernameAndPassword() {
    }

    @org.junit.Test
    public void saveUser() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null,"kiki","1111","kiki@qq.com")));
    }
}