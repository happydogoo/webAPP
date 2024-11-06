package com.happydog.persistence;

import java.sql.*;

public class DBConnectionManager {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/web_application";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭数据库连接失败！");
            e.printStackTrace();
        }
    }


    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭Statement失败！");
            e.printStackTrace();
        }
    }


    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭PreparedStatement失败！");
            e.printStackTrace();
        }
    }

    /**
     * 关闭ResultSet对象
     *
     * @param resultSet 查询结果集对象
     */
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭ResultSet失败！");
            e.printStackTrace();
        }
    }

    public static class LogDao {
    }
}
