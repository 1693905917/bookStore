package com.hzp.service;

import com.hzp.pojo.Cart;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-02-03 20:45
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
