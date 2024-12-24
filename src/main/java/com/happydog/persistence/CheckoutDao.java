package com.happydog.persistence;

import com.happydog.model.Order;
import com.happydog.persistence.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDao {

    public boolean insertOrder(Order order) {
        String sql = "INSERT INTO orders (username, orderdate, shipaddr1, shipzip, shipcountry, courier, " +
                "totalprice, shiptoname, creditcard, cardtype, itemId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("checkout dao insertOrder "+order.toString());
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getUsername());
            stmt.setDate(2, order.getOrderDate());
            stmt.setString(3, order.getShipAddr1());
            stmt.setString(4, order.getShipZip());
            stmt.setString(5, order.getShipCountry());
            
            stmt.setString(6, order.getCourier());
            stmt.setBigDecimal(7, order.getTotalPrice());
            stmt.setString(8, order.getShipToName());
            stmt.setString(9, order.getCreditCard());
            stmt.setString(10, order.getCardType());
            stmt.setString(11, order.getItemId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Order> getAllOrders(String username) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders"; // 查询所有订单的SQL语句

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                if(username.equals(rs.getString("username"))) {
                    Order order = new Order();
                    order.setOrderId(rs.getString("orderid")); // 假设有getOrderId方法
                    order.setUsername(rs.getString("username"));
                    order.setOrderDate(rs.getDate("orderdate"));
                    order.setShipAddr1(rs.getString("shipaddr1"));
                    order.setShipZip(rs.getString("shipzip"));
                    order.setShipCountry(rs.getString("shipcountry"));
                    order.setCourier(rs.getString("courier"));
                    order.setTotalPrice(rs.getBigDecimal("totalprice"));
                    order.setShipToName(rs.getString("shiptoname"));
                    order.setCreditCard(rs.getString("creditcard"));
                    order.setCardType(rs.getString("cardtype"));
                    order.setItemId(rs.getString("itemId"));

                    orders.add(order); // 添加到订单列表中
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders; // 返回订单列表
    }
}
