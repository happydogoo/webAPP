package com.happydog.service;

import com.happydog.model.Log;
import com.happydog.persistence.DBConnectionManager;
import com.happydog.persistence.LogDao;

public class LogService {
    private LogDao logDao=new LogDao();

    public void recordProductClickLog(Log log){
        log.setAction("浏览商品");
        logDao.insertLog(log);
    }
    public void addItemToCartLog(Log log){
        log.setAction("添加购物车");
        logDao.insertLog(log);
    }
    public void checkoutLog(Log log){
        log.setAction("生成订单");
        logDao.insertLog(log);
    }
}
