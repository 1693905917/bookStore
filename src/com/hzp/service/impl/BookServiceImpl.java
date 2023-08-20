package com.hzp.service.impl;

import com.hzp.dao.BookDao;
import com.hzp.dao.impl.BookDaoImpl;
import com.hzp.pojo.Book;
import com.hzp.pojo.Page;
import com.hzp.service.BookService;

import java.util.List;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-26 08:45
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Integer count=0;
        int begin=0;
        BookDaoImpl bookDao = new BookDaoImpl();
        Page<Book> page=new Page<>();


        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录
        Integer totalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(totalCount);
        //求总页码
        count=page.getPageTotalCount()/pageSize;
        if(page.getPageTotalCount()%pageSize>0){
            count++;
        }
        //设置总页码
        page.setPageTotal(count);

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        begin=(page.getPageNo()-1)*pageSize;
        //求当前数据
        List<Book> list = bookDao.queryForPageItems(begin, pageSize);
        //设置当前数据
        page.setItems(list);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Integer count=0;
        int begin=0;
        BookDaoImpl bookDao = new BookDaoImpl();
        Page<Book> page=new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录
        Integer totalCount = bookDao.queryForPageTotalCountByPrice( min,max);
        //设置总记录数
        page.setPageTotalCount(totalCount);
        //求总页码
        count=page.getPageTotalCount()/pageSize;
        if(page.getPageTotalCount()%pageSize>0){
            count++;
        }
        //设置总页码
        page.setPageTotal(count);

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        begin=(page.getPageNo()-1)*pageSize;
        //求当前数据
        List<Book> list = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //设置当前数据
        page.setItems(list);
        return page;
    }
}
