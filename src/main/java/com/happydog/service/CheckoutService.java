package com.happydog.service;

import com.happydog.persistence.CheckoutDao;
import com.happydog.model.Order;

import java.util.List;

public class CheckoutService {
    private CheckoutDao checkoutDao = new CheckoutDao();

    public boolean processOrder(Order order) {
        return checkoutDao.insertOrder(order);
    }


}
