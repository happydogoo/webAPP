package com.happydog.persistence;

import com.happydog.model.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDao {

    // 插入日志的SQL语句
    private static final String INSERT_LOG_SQL = "INSERT INTO log (user_id, username, action, target_type, target_id, target_name, action_time, ip_address) "
            + "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)";

    // 插入日志记录的方法
    public boolean insertLog(Log log) {
        boolean rowInserted = false;

        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG_SQL)) {

            // 设置日志信息
            preparedStatement.setString(1, log.getUserId());
            preparedStatement.setString(2, log.getUsername());
            preparedStatement.setString(3, log.getAction());
            preparedStatement.setString(4, log.getTargetType());
            preparedStatement.setString(5, log.getTargetId());
            preparedStatement.setString(6, log.getTargetName());
            preparedStatement.setString(7, log.getIpAddress());

            // 执行插入
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // 实际项目中需要更为详细的异常处理
        }

        return rowInserted;
    }
}
