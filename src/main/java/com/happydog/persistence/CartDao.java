package com.happydog.persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happydog.model.Cart;
import com.happydog.model.Item;
import com.happydog.persistence.DBConnectionManager;


public class CartDao {

    // 添加商品到购物车
    public boolean addItemToCart(String itemId, String username, BigDecimal price) {
        String sql = "INSERT INTO cart (itemid, username, status,price) VALUES (?, ?, 1, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemId);
            stmt.setString(2, username);
            stmt.setBigDecimal(4,price);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 从购物车删除商品，新加入的用1表示
    public boolean removeItemFromCart(String itemId, String username) {
        String sql = "DELETE FROM cart WHERE itemid = ? AND username = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemId);
            stmt.setString(2, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //已经卖掉的用0标记
    public boolean sellItem(String itemId, String username) {
        String sql = "UPDATE cart SET status = 0 WHERE itemid = ? AND username = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemId);
            stmt.setString(2, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Item> getCartItems(String username){
        List<Item> itemList=new ArrayList<>();
        String sql = "SELECT itemid, status, price FROM cart WHERE username = ?";

        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String itemId = resultSet.getString("itemid");
                int status = resultSet.getInt("status"); // 假设 status 是 int 类型
                BigDecimal price = resultSet.getBigDecimal("price"); // 假设 price 是 BigDecimal 类型

                // 创建 Item 对象并添加到列表中
                Item item = new Item();
                item.setItemId(itemId);
                if(status==0) {
                    item.setCartStatus(false);
                }
                else {
                    item.setCartStatus(true);
                }
                item.setListPrice(price);
                itemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 错误处理，可以记录日志或抛出自定义异常
        }
        System.out.println(itemList.toString()+"123123123213");
        return itemList;
    }

}

