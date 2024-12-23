package com.happydog.persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happydog.model.Cart;
import com.happydog.model.CheckoutInfo;
import com.happydog.model.Item;
import com.happydog.persistence.DBConnectionManager;


public class CartDao {
    private ItemDao itemDao=new ItemDao();

    // 添加商品到购物车
    public boolean addItemToCart(String itemId, String username, BigDecimal price,int quantity) {
        String sql = "INSERT INTO cart (itemid, username, status,price,quantity) VALUES (?, ?, 1, ?,?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemId);
            stmt.setString(2, username);
            stmt.setBigDecimal(3,price);
            stmt.setInt(4,quantity);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateItemQuantity(String itemId,String username,int newQuantity){
        String sql = "UPDATE cart SET quantity = ? WHERE itemid = ? AND username = ? AND status = 1";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 设置参数
            stmt.setInt(1, newQuantity);
            stmt.setString(2, itemId);
            stmt.setString(3, username);

            // 执行更新操作
            int rowsAffected = stmt.executeUpdate();

            System.out.println("sql updating");
            // 如果更新成功，返回 true
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("sql update false");
        return false;
    }

    // 从购物车删除商品，新加入的用1表示
    public boolean removeItemFromCart(String itemId, String username) {
        String sql = "DELETE FROM cart WHERE itemid = ? AND username = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        //    System.out.println("delete database cart"+username+itemId);
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
        String sql = "SELECT itemid, status, price,quantity FROM cart WHERE username = ?";

        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String itemId = resultSet.getString("itemid");
                int status = resultSet.getInt("status"); // 假设 status 是 int 类型
                BigDecimal price = resultSet.getBigDecimal("price"); // 假设 price 是 BigDecimal 类型
                int quantity=resultSet.getInt("quantity");
                if(status==1){
                // 创建 Item 对象并添加到列表中
                    Item item =itemDao.getItem(itemId);
                
                if(status==0) {
                    item.setCartStatus(false);
                }
                else {
                    item.setCartStatus(true);
                }
                item.setListPrice(price);
                item.setQuantity(quantity);
                itemList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 错误处理，可以记录日志或抛出自定义异常
        }
        System.out.println(itemList.toString()+"123123123213");
        return itemList;
    }


        public boolean saveCheckoutInfo(CheckoutInfo order) {
            String sql = "INSERT INTO CheckoutInfo (username, order_date, ship_addr1, ship_zip, ship_country, courier, ship_to_name, credit_card, card_type) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DBConnectionManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, order.getUsername());
                stmt.setDate(2, order.getOrderDate());
                stmt.setString(3, order.getShipAddr1());
                stmt.setString(4, order.getShipZip());
                stmt.setString(5, order.getShipCountry());
                stmt.setString(6, order.getCourier());
                stmt.setString(7, order.getShipToName());
                stmt.setString(8, order.getCreditCard());
                stmt.setString(9, order.getCardType());


                int rowsAffected = stmt.executeUpdate();
                System.out.println("Checkout info saved successfully!");

                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }



    public List<CheckoutInfo> getCheckoutInfoByUsername(String username) {
        String sql = "SELECT username, order_date, ship_addr1, ship_zip, ship_country, courier, ship_to_name, credit_card, card_type "
                + "FROM CheckoutInfo WHERE username = ?";
        CheckoutInfo checkoutInfo = null;
        List<CheckoutInfo> checkoutInfoList=new ArrayList<>();
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    checkoutInfo = new CheckoutInfo();
                    checkoutInfo.setUsername(rs.getString("username"));
                    checkoutInfo.setOrderDate(rs.getDate("order_date"));
                    checkoutInfo.setShipAddr1(rs.getString("ship_addr1"));
                    checkoutInfo.setShipZip(rs.getString("ship_zip"));
                    checkoutInfo.setShipCountry(rs.getString("ship_country"));
                    checkoutInfo.setCourier(rs.getString("courier"));
                     checkoutInfo.setShipToName(rs.getString("ship_to_name"));
                    checkoutInfo.setCreditCard(rs.getString("credit_card"));
                    checkoutInfo.setCardType(rs.getString("card_type"));
                    checkoutInfoList.add(checkoutInfo);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(checkoutInfoList.toString());
        return checkoutInfoList;
    }


}

