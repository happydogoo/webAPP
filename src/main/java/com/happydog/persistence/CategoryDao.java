package com.happydog.persistence;

import com.happydog.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private  static final String GET_CATEGORY_LIST="SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
    private  static  final  String GET_CATEGORY=
            "SELECT CATID AS categoryId, NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?";
    public List<Category>getCategoryList(){
List<Category> categoryList=new ArrayList<>();
try{
    Connection connection=DBConnectionManager.getConnection();
    Statement statement =connection.createStatement();
    ResultSet resultSet=statement.executeQuery(GET_CATEGORY_LIST);
    while(resultSet.next()){
        Category category=new Category();
        category.setCategoryId(resultSet.getString("categoryId"));
        category.setName(resultSet.getString("NAME"));
        category.setDescription(resultSet.getString("description"));
        categoryList.add(category);
    }
}catch (Exception e){
    e.printStackTrace();
}
    return categoryList;
    }


    public Category getCategory(String categoryId){
        Category category=null;
        try{

            Connection connection=DBConnectionManager.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()) {
            category = new Category();
            category.setCategoryId(resultSet.getString("categoryId"));
            category.setName(resultSet.getString("NAME"));
            category.setDescription(resultSet.getString("description"));
        }
            DBConnectionManager.close(resultSet);
            DBConnectionManager.close(preparedStatement);
            DBConnectionManager.close(connection);
        }
        catch(Exception e){
            e.printStackTrace();
            }
        return category;
    }


 }




