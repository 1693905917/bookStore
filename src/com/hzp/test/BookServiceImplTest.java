package com.hzp.test;

import com.hzp.pojo.Book;
import com.hzp.pojo.Page;
import com.hzp.service.impl.BookServiceImpl;

import static org.junit.Assert.*;

public class BookServiceImplTest extends Test {

    @org.junit.Test
    public void page() {
        Page<Book> page = new BookServiceImpl().page(1, 4);
        System.out.println(page.toString());
    }
    @org.junit.Test
    public void pageByPrice() {
        Page<Book> page = new BookServiceImpl().pageByPrice(0,4,10,50);
        System.out.println(page.toString());
    }
}