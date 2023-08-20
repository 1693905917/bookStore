package com.hzp.test;

import com.hzp.dao.BookDao;
import com.hzp.dao.impl.BookDaoImpl;
import com.hzp.pojo.Book;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoImplTest extends Test {
    BookDao bookDao = new BookDaoImpl();
    @org.junit.Test
    public void addBook() {
        System.out.println(bookDao.addBook(new Book("小鼠","惨兮兮",new BigDecimal(23),40,28,"static/img/default.jpg")));
    }

    @org.junit.Test
    public void deleteBookById() {
        System.out.println(bookDao.deleteBookById(24));
    }

    @org.junit.Test
    public void updateBook() {
        System.out.println(bookDao.updateBook(new Book(24,"小鼠","西安",new BigDecimal(23),40,28,"static/img/default.jpg")));
    }

    @org.junit.Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(24));
    }

    @org.junit.Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }

    @org.junit.Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @org.junit.Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(0,4));
    }

    @org.junit.Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @org.junit.Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(0,4,10,50));
    }
}