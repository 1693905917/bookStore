package com.hzp.web;

import com.hzp.pojo.Cart;
import com.hzp.pojo.User;
import com.hzp.service.OrderService;
import com.hzp.service.impl.OrderServiceImpl;
import com.hzp.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-02-03 20:55
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 先获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
// 获取 Userid
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
// 调用 orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart,userId);
// req.setAttribute("orderId", orderId);
// 请求转发到/pages/cart/checkout.jsp
// req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        //pages/cart/checkout.jsp
    }
}
