package com.hzp.service;

import com.hzp.pojo.Book;
import com.hzp.pojo.Page;

import java.util.List;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-26 08:42
 */
public interface  BookService {

    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo,int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
