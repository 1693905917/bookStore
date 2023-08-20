package com.hzp.web;

import com.hzp.pojo.User;
import com.hzp.service.UserService;
import com.hzp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ASUS
 * @projectName book
 * @description: 登录界面
 * @date 2022-01-22 10:18
 */
//@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("html/text;charset=utf-8");
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        UserService userService=new UserServiceImpl();
//        User login = userService.login(new User(null, username, password, null));
//        if(login.getUsername()!=null){
//            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
//        }else{
//            request.setAttribute("msg","用户名或者密码错误");
//            request.setAttribute("username",username);
//            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
//        }
//
//    }
}
