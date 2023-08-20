package com.hzp.dao.impl;

import com.hzp.dao.OrderItemDao;
import com.hzp.pojo.OrderItem;
import com.hzp.utils.JdbcUtils;

import java.sql.*;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-02-03 20:06
 */
public class OrderItemDaoImpl implements OrderItemDao {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps=null;
    @Override
    public boolean saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) " +
                "values(?,?,?,?,?)";
         conn = JdbcUtils.getConn();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,orderItem.getName());
            ps.setInt(2,orderItem.getCount());
            ps.setBigDecimal(3,orderItem.getPrice());
            ps.setBigDecimal(4,orderItem.getTotalPrice());
            ps.setString(5,orderItem.getOrderId());
            return  ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
