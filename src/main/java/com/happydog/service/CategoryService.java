package com.happydog.service;

import com.happydog.model.Category;
import com.happydog.model.Item;
import com.happydog.model.Product;
import com.happydog.persistence.CategoryDao;
import com.happydog.persistence.ItemDao;
import com.happydog.persistence.ProductDao;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private ItemDao itemDao;
    public CategoryService(){
        this.categoryDao=new CategoryDao();
        this.productDao=new ProductDao();
        this.itemDao=new ItemDao();
    }

    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String category) {
        return productDao.getProductListByCategory(category);
    }


    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }

}
