package com.happydog.service;

import com.happydog.model.Item;
import com.happydog.persistence.ItemDao;
import com.happydog.persistence.ProductDao;
import com.happydog.model.Product;

import java.util.List;

public class ProductService {
    private ItemDao itemDao=new ItemDao();
    private ProductDao productDao=new ProductDao();



    public List<Item> getItemById(String productId) {
        return itemDao.getItemListByProduct(productId);
    }
    public List<Product> searchProduct(String query){
        return productDao.searchProductList(query);
    }

}
