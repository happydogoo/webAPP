package com.happydog.model;

import java.math.BigDecimal;
import java.sql.Date;

public class CheckoutInfo {

    private String username;       // 用户名
    private Date orderDate;        // 订单日期
    private String shipAddr1;      // 收货地址
    private String shipZip;        // 收货邮编
    private String shipCountry;    // 收货国家
    private String courier;        // 快递公司
    private BigDecimal totalPrice; // 总价格
    private String shipToName;     // 收货人姓名
    private String creditCard;     // 信用卡号
    private String cardType;       // 卡类型


    // 默认构造函数
    public CheckoutInfo() {}

    // 参数化构造函数
    public CheckoutInfo(String username, Date orderDate, String shipAddr1, String shipZip, String shipCountry,
                        String courier, String shipToName, String creditCard, String cardType) {
        this.username = username;
        this.orderDate = orderDate;
        this.shipAddr1 = shipAddr1;
        this.shipZip = shipZip;
        this.shipCountry = shipCountry;
        this.courier = courier;

        this.shipToName = shipToName;
        this.creditCard = creditCard;
        this.cardType = cardType;

    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipAddr1() {
        return shipAddr1;
    }

    public void setShipAddr1(String shipAddr1) {
        this.shipAddr1 = shipAddr1;
    }

    public String getShipZip() {
        return shipZip;
    }

    public void setShipZip(String shipZip) {
        this.shipZip = shipZip;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }


    public String getShipToName() {
        return shipToName;
    }

    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }



    @Override
    public String toString() {
        return "CheckoutInfo{" +
                "username='" + username + '\'' +
                ", orderDate=" + orderDate +
                ", shipAddr1='" + shipAddr1 + '\'' +
                ", shipZip='" + shipZip + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                ", courier='" + courier + '\'' +

                ", shipToName='" + shipToName + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
