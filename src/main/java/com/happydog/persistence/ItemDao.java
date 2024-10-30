package com.happydog.persistence;

import com.happydog.model.Item;
import java.sql.*;
import java.util.*;

public class ItemDao {

    private static final String UPDATE_INVENTORY_QUANTITY = "UPDATE item SET quantity = ? WHERE itemid = ?";
    private static final String GET_INVENTORY_QUANTITY = "SELECT quantity FROM item WHERE itemid = ?";
    private static final String GET_ITEM_LIST_BY_PRODUCT = "SELECT * FROM item WHERE productid = ?";
    private static final String GET_ITEM = "SELECT * FROM item WHERE itemid = ?";

    // 更新库存数量
    public void updateInventoryQuantity(Map<String, Object> param) {
        String itemId = (String) param.get("itemId");
        Integer quantity = (Integer) param.get("quantity");
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY)) {
            statement.setInt(1, quantity);
            statement.setString(2, itemId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询库存数量
    public int getInventoryQuantity(String itemId) {
        int quantity = 0;
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_INVENTORY_QUANTITY)) {
            statement.setString(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    // 根据 productId 查询商品列表
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT)) {
            statement.setString(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setItemId(resultSet.getString("itemid"));
                    item.setProductId(resultSet.getString("productid"));
                    item.setListPrice(resultSet.getBigDecimal("listprice"));
                    item.setUnitCost(resultSet.getBigDecimal("unitcost"));
                    item.setSupplierId(resultSet.getInt("supplier"));
                    item.setStatus(resultSet.getString("status"));
                    item.setAttribute1(resultSet.getString("attr1"));
                    itemList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    // 根据 itemId 查询单个商品信息
    public Item getItem(String itemId) {
        Item item = null;
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ITEM)) {
            statement.setString(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new Item();
                    item.setItemId(resultSet.getString("itemid"));
                    item.setProductId(resultSet.getString("productid"));
                    item.setListPrice(resultSet.getBigDecimal("listprice"));
                    item.setUnitCost(resultSet.getBigDecimal("unitcost"));
                    item.setSupplierId(resultSet.getInt("supplier"));
                    item.setStatus(resultSet.getString("status"));
                    item.setAttribute1(resultSet.getString("attr1"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
