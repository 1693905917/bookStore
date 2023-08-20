package com.hzp.test;

import com.hzp.pojo.Cart;
import com.hzp.pojo.CartItem;
import com.hzp.service.OrderService;
import com.hzp.service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends Test {

    @org.junit.Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }
}