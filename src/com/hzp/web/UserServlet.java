package com.hzp.web;

import com.google.gson.Gson;
import com.hzp.dao.impl.UserDaoImpl;
import com.hzp.pojo.User;
import com.hzp.service.UserService;
import com.hzp.service.impl.UserServiceImpl;
import com.hzp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-25 16:26
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean b = new UserServiceImpl().existUsername(username);
        Map<String, Object> stringObjectsMap=new HashMap<>();
        stringObjectsMap.put("b",b);
        Gson gson = new Gson();
        String s = gson.toJson(stringObjectsMap);
        response.getWriter().write(s);
    }
    /**
     * 注销
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        System.out.println(request.getContextPath());
       response.sendRedirect(request.getContextPath()+"/client/clientBookServlet?action=page");
//        request.getRequestDispatcher("/pages/client/index.jsp").forward();
    }
    /**
     * 登录界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User login = userService.login(new User(null, username, password, null));
        UserService userService=new UserServiceImpl();
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
        User login = userService.login(user);
        if(login.getUsername()!=null){
            request.getSession().setAttribute("user",login);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","用户名或者密码错误");
            request.setAttribute("username",user.getUsername());
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
    }

    /**
     * 注册界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");
        // 获取 Session 中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        UserService userService = new UserServiceImpl();

        String code = request.getParameter("code");

        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
        //先验证  验证码的  假设验证码为abcde
        if(token.equals(code)){
            //输入正确
            //判断用户名是否存在
            if(userService.existUsername(user.getUsername())){
                //用户名存在
                request.setAttribute("msg","用户名存在");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("email",user.getEmail());
                request.setAttribute("code",code);
                System.out.println("该"+user.getUsername()+"存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //用户不存在
                userService.registerUser(user);
                System.out.println("保存成功！！");
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else {
            //输入验证码错误
            System.out.println("您输入的"+code+"错误");
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username",user.getUsername());
            request.setAttribute("email",user.getEmail());
            request.setAttribute("code",code);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }

}
