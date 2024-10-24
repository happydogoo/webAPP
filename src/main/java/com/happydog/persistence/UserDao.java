package com.happydog.persistence;

import com.happydog.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public boolean saveUser(User user) {
        String sql = "INSERT INTO account (email, username, address, phone, password) VALUES (?, ?, ?, ?, ?)";

         try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Executing userDAO query");

            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getAddress());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getPassword());


            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 返回是否插入成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
