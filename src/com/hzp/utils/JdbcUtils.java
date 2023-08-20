package com.hzp.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * @author ASUS
 * @projectName book
 * @description: 工具类
 * @date 2022-01-20 19:20
 */
public class JdbcUtils {
    private static Connection conn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static String Driver = null;
    private static String Url = null;
    private static String username = null;
    private static String password = null;

    private static ThreadLocal<Connection> conns=new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream("D://Java//IDEA_Practice//book//src//com//hzp//utils//db.properties")));
            Driver = properties.getProperty("driver");
            Url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static Connection getConn() {
//        try {
//            Class.forName(Driver);
//            conn = DriverManager.getConnection(Url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }

    /**
     * 获取数据库连接池中的连接
     * @return  如果返回null,说明获取连接失败  有值就是获取连接成功
     */
    public static Connection getConn() {
        try {
            Class.forName(Driver);
            conn = conns.get();
            if(conn==null){
                conn = DriverManager.getConnection(Url, username, password);
                conns.set(conn);//保存到threadLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false);//设置为手动管理事务
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

//    public static void Close(Connection conn) {
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        conn=conns.get();
            if(conn!=null){  //如果不等于null  说明之前使用过连接，操作过数据库
                try {
                    conn.commit();//提交事务
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            //一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
            conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        conn=conns.get();
        if(conn!=null){  //如果不等于null  说明之前使用过连接，操作过数据库
            try {
                conn.rollback();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }




}
