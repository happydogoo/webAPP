package com.happydog.persistence;

import com.happydog.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String GET_PRODUCT_LIST_BY_CATEGORY =
            "SELECT PRODUCTID AS productId, NAME, DESCN AS description, IMAGEURL AS imageUrl FROM PRODUCT WHERE CATEGORY = ?";
    private static final String GET_PRODUCT =
            "SELECT PRODUCTID AS productId, NAME, DESCN AS description, IMAGEURL AS imageUrl FROM PRODUCT WHERE NAME = ?";
    private static final String SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID AS productId, NAME, DESCN AS description, IMAGEURL AS imageUrl FROM PRODUCT WHERE NAME LIKE ? OR DESCN LIKE ?";

    public List<Product> getProductListByCategory(String category) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DBConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("productId")); // 设置 productId
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                productList.add(product);
            }
            DBConnectionManager.close(resultSet);
            DBConnectionManager.close(preparedStatement);
            DBConnectionManager.close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getProduct(String name) {
        Product product = null;
        try {
            Connection connection = DBConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString("productId")); // 设置 productId
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("imageUrl"));
            }
            DBConnectionManager.close(resultSet);
            DBConnectionManager.close(preparedStatement);
            DBConnectionManager.close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DBConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            String keywordPattern = "%" + keywords + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("productId")); // 设置 productId
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                productList.add(product);
            }
            DBConnectionManager.close(resultSet);
            DBConnectionManager.close(preparedStatement);
            DBConnectionManager.close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
