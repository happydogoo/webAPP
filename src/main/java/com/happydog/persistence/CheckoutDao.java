package com.happydog.persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckoutDao {

    // 插入订单信息的方法
    public boolean insertOrder(String username, Date orderDate, String shipAddr1, String shipZip,
                               String shipCountry, String courier, BigDecimal totalPrice,
                               String name, String creditCard, String cardType) {
        String sql = "INSERT INTO orders (username, orderdate, shipaddr1, shipzip, shipcountry, " +
                "courier, totalprice, shiptoname, creditcard, cardtype) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setDate(2, orderDate);
            stmt.setString(3, shipAddr1);
            stmt.setString(4, shipZip);
            stmt.setString(5, shipCountry);
            stmt.setString(6, courier);
            stmt.setBigDecimal(7, totalPrice);
            stmt.setString(8, name);
            stmt.setString(9, creditCard);
            stmt.setString(10, cardType);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // 如果影响的行数大于0，返回true

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 发生异常时返回false
        }
    }
}
