package com.hzp.dao.impl;

import com.hzp.dao.OrderDao;
import com.hzp.pojo.Order;
import com.hzp.utils.JdbcUtils;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-02-03 20:06
 */
public class OrderDaoImpl implements OrderDao {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps=null;
    @Override
    public boolean saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) " +
                "values(?,?,?,?,?)";
         conn = JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,order.getOrderId());
            ps.setDate(2,
                     new java.sql.Date(order.getCreateTime().getTime()));
            ps.setBigDecimal(3,order.getPrice());
            ps.setInt(4,order.getStatus());
            ps.setInt(5,order.getUserId());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
