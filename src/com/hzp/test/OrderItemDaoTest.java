package com.hzp.test;

import com.hzp.dao.OrderDao;
import com.hzp.dao.OrderItemDao;
import com.hzp.dao.impl.OrderDaoImpl;
import com.hzp.dao.impl.OrderItemDaoImpl;
import com.hzp.pojo.Order;
import com.hzp.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderItemDaoTest extends Test {

    @org.junit.Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao= new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567891"));
    }

    @org.junit.Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}