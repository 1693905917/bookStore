package com.hzp.web;

import com.hzp.pojo.Book;
import com.hzp.pojo.Page;
import com.hzp.service.BookService;
import com.hzp.service.impl.BookServiceImpl;
import com.hzp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-28 16:09
 */
@WebServlet("/client/clientBookServlet")
public class ClientBookServlet extends BaseServlet{
    BookService bookService=new BookServiceImpl();

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了分页");
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/clientBookServlet?action=page");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    /**
     * 通过价格查询商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);
        StringBuffer sb = new StringBuffer("client/clientBookServlet?action=pageByPrice");
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
