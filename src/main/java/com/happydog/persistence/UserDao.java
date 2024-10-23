package com.happydog.persistence;


import com.happydog.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {



    private static final String DB_URL = "jdbc:mysql://localhost:3306/web_application";
    private static final String DB_USER = "account";

    //根据自己的数据库密码来替换
    private static final String DB_PASSWORD = "";

    public boolean saveUser(User user) {
        String sql = "INSERT INTO account (email, username, address, phone, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置参数
            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getAddress());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getPassword());

            // 执行插入操作
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 返回是否插入成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 出现异常时返回失败
        }
    }
}
