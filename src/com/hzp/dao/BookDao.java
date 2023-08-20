package com.hzp.dao;

import com.hzp.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 通过ID删除图书
     * @param id
     * @return
     */
    boolean deleteBookById(Integer id);

    /**
     * 通过book修改图书
     * @param book
     * @return
     */
    boolean updateBook(Book book);

    /**
     * 通过ID查询book
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 总记录数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 当前页面的数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin,int pageSize);

    Integer queryForPageTotalCountByPrice(int min,int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
