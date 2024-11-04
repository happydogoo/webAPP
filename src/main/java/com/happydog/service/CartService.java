package com.happydog.service;

import com.happydog.model.Item;
import com.happydog.persistence.CartDao;
import com.happydog.persistence.CategoryDao;
import com.happydog.persistence.ItemDao;
import com.happydog.persistence.ProductDao;

import java.util.List;

public class CartService {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private ItemDao itemDao;
    private CartDao cartDao;
    public CartService(){
        this.categoryDao=new CategoryDao();
        this.productDao=new ProductDao();
        this.itemDao=new ItemDao();
        this.cartDao=new CartDao();
    }

    public boolean addItemToCart(String itemId, String username) {

        Item item=itemDao.getItem(itemId);
        return cartDao.addItemToCart(itemId, username,item.getListPrice());
    }
    // 将购物车中的商品标记为已售出
    public boolean sellItem(String itemId, String username) {
        return cartDao.sellItem(itemId, username);
    }

    // 查询购物车中某用户的所有商品
    public List<Item> getUserCartItems(String username) {


        return cartDao.getCartItems(username);
    }

    // 移除购物车中的指定商品
    public boolean removeItemFromCart(String itemId, String username) {
        System.out.println("delete database cart"+username+itemId);
        return cartDao.removeItemFromCart(itemId, username);
    }



}
