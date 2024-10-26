package com.happydog.persistence;

import com.happydog.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public boolean checkLogin(String username,String password){
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                // 如果结果集中有数据，说明用户名和密码匹配
                return rs.next(); // 返回 true 如果找到了匹配的用户
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 出现异常时返回 false
        }
    }

}
