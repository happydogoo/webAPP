package com.happydog.service;

import com.happydog.model.Order;
import com.happydog.persistence.CheckoutDao;

import java.util.List;

public class OrderService {
    private CheckoutDao checkoutDao=new CheckoutDao();
    public List<Order> getOrders(String username){return checkoutDao.getAllOrders(username);}


}
