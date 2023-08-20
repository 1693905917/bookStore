package com.hzp.dao.impl;

import com.hzp.dao.UserDao;
import com.hzp.pojo.User;
import com.hzp.utils.JdbcUtils;

import java.sql.*;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-21 20:44
 */
public class UserDaoImpl implements UserDao {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps=null;
    @Override
    public User queryUserByUsername(String username) {
        conn=JdbcUtils.getConn();
        try {
            String sql="select * from `t_user` where username=?";
             ps= conn.prepareStatement(sql);
             ps.setString(1,username);
            return ExecuteQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        conn=JdbcUtils.getConn();
        try {
            String sql="select * from `t_user` where username=?and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            return ExecuteQuery();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveUser(User user) {
        String sql="INSERT INTO `t_user`(`username`,`password`,`email`) VALUES(?,?,?);";
        conn=JdbcUtils.getConn();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private User ExecuteQuery() throws SQLException {
        rs= ps.executeQuery();
        User user=new User();
        while(rs.next()){
            user.setId(Integer.parseInt(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
        }
        return user;
    }
}
