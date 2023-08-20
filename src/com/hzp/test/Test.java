package com.hzp.test;

import com.hzp.dao.impl.UserDaoImpl;
import com.hzp.utils.JdbcUtils;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-20 19:38
 */
public class Test {
@org.junit.Test
    public void test(){
    UserDaoImpl userDao = new UserDaoImpl();
    System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
}
}
