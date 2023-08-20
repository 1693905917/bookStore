package com.hzp.web;

import com.google.gson.Gson;
import com.hzp.pojo.Book;
import com.hzp.pojo.Cart;
import com.hzp.pojo.CartItem;
import com.hzp.service.BookService;
import com.hzp.service.impl.BookServiceImpl;
import com.hzp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-02-01 22:05
 */
@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet{
   BookService bookService=new BookServiceImpl();

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入修改");
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }
    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    /**
     * 删除购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入删除");
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart =(Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    /**
     * 添加商品到购物车中
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入购物车");
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(), book.getName(), 1,book.getPrice(),book.getPrice());

        Cart cart=(Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        request.getSession().setAttribute("lastName",cartItem.getName());

        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(), book.getName(), 1,book.getPrice(),book.getPrice());

        Cart cart=(Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        request.getSession().setAttribute("lastName",cartItem.getName());

        Map<String,Object> map=new HashMap<>();
        map.put("lastName",cartItem.getName());
        map.put("totalCount",cart.getTotalCount());
        Gson gson = new Gson();
        String s = gson.toJson(map);
        response.getWriter().write(s);

    }
}
