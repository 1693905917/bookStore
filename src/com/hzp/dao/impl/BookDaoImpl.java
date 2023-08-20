package com.hzp.dao.impl;

import com.hzp.dao.BookDao;
import com.hzp.pojo.Book;
import com.hzp.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-25 20:30
 */
public class BookDaoImpl implements BookDao {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps=null;
    @Override
    public boolean addBook(Book book) {
         conn= JdbcUtils.getConn();
         String sql="INSERT INTO t_book( `name` , `author` , `price` , `sales` , `stock` , `img_path`) " +
                 "VALUES(?,?,?,?,?,?);";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,book.getName());
            ps.setString(2,book.getAuthor());
           ps.setBigDecimal(3,book.getPrice());
            ps.setInt(4,book.getSales());
            ps.setInt(5,book.getStock());
            ps.setString(6,book.getImgPath());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBook(Book book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,book.getName());
            ps.setString(2,book.getAuthor());
            ps.setDouble(3,book.getPrice().doubleValue());
            ps.setInt(4,book.getSales());
            ps.setInt(5,book.getStock());
            ps.setString(6,book.getImgPath());
            ps.setInt(7,book.getId());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select * from t_book where id=?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            Book book = new Book();
            while(rs.next()){
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
               book.setAuthor(rs.getString("author"));
               book.setPrice(rs.getBigDecimal("price"));
               book.setSales(rs.getInt("sales"));
               book.setStock(rs.getInt("stock"));
               book.setImgPath(rs.getString("img_path"));
            }
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from t_book";
        conn=JdbcUtils.getConn();
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            List<Book> list=new ArrayList<>();
            while(rs.next()){
                Book book=new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setSales(rs.getInt("sales"));
                book.setStock(rs.getInt("stock"));
                book.setImgPath(rs.getString("img_path"));
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer queryForPageTotalCount() {
        int count=0;
        String sql="select Count(*) from t_book";
        conn = JdbcUtils.getConn();
        try {
            st=conn.createStatement();
            rs= st.executeQuery(sql);
            while(rs.next()){
                count=rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select * from t_book limit ?,?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,begin);
            ps.setInt(2,pageSize);
            rs = ps.executeQuery();
            List<Book> list=new ArrayList<Book>();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setSales(rs.getInt("sales"));
                book.setStock(rs.getInt("stock"));
                book.setImgPath(rs.getString("img_path"));
                list.add(book);
            }
            return list;

        }  catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        int count=0;
        String sql="select Count(*) from t_book where price between ? and ?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,min);
            ps.setInt(2,max);
            rs=ps.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            return count;
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select * from t_book where price between ? and ? order by price limit ?,?";
        conn=JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,min);
            ps.setInt(2,max);
            ps.setInt(3,begin);
            ps.setInt(4,pageSize);
            rs = ps.executeQuery();
            List<Book> list=new ArrayList<Book>();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setSales(rs.getInt("sales"));
                book.setStock(rs.getInt("stock"));
                book.setImgPath(rs.getString("img_path"));
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
