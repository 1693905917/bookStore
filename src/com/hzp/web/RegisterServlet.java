package com.hzp.web;

import com.hzp.dao.impl.UserDaoImpl;
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
 * @description: 注册页面
 * @date 2022-01-21 22:14
 */
//@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("html/text;charset=utf-8");
//
//        UserService userService = new UserServiceImpl();
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String code = request.getParameter("code");
//        //先验证  验证码的  假设验证码为abcde
//        UserDaoImpl userDao = new UserDaoImpl();
//        System.out.println(userDao.queryUserByUsername(username));
//        if("abcde".equals(code)){
//            //输入正确
//            //判断用户名是否存在
//            if(userService.existUsername(username)){
//                //用户名存在
//                request.setAttribute("msg","用户名存在");
//                request.setAttribute("username",username);
//                request.setAttribute("email",email);
//                request.setAttribute("code",code);
//                System.out.println("该"+username+"存在");
//                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
//            }else{
//                //用户不存在
//                userService.registerUser(new User(null,username,password,email));
//                System.out.println("保存成功！！");
//                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
//            }
//        }else {
//            //输入验证码错误
//            System.out.println("您输入的"+code+"错误");
//            request.setAttribute("msg","验证码错误");
//            request.setAttribute("username",username);
//            request.setAttribute("email",email);
//            request.setAttribute("code",code);
//            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
//        }
//    }
}
