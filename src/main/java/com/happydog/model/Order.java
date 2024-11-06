package com.happydog.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private String orderId;          // 订单 ID（主键）
    private String username;       // 用户名
    private Date orderDate;        // 订单日期
    private String shipAddr1;      // 收货地址1
    private String shipAddr2;      // 收货地址2（可选）
    private String shipCity;       // 收货城市
    private String shipState;      // 收货省份/州
    private String shipZip;        // 邮政编码
    private String shipCountry;    // 国家
    private String billAddr1;      // 账单地址1
    private String billAddr2;      // 账单地址2（可选）
    private String billCity;       // 账单城市
    private String billState;      // 账单省份/州
    private String billZip;        // 账单邮政编码
    private String billCountry;    // 账单国家
    private String courier;        // 快递公司
    private BigDecimal totalPrice; // 订单总价
    private String shipToName;     // 收货人姓名
    private String creditCard;     // 信用卡号
    private String cardType;       // 信用卡类型
    private String itemId;         // 商品ID
    private int quantity;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getShipAddr2() {
        return shipAddr2;
    }

    public void setShipAddr2(String shipAddr2) {
        this.shipAddr2 = shipAddr2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
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

    public String getBillAddr1() {
        return billAddr1;
    }

    public void setBillAddr1(String billAddr1) {
        this.billAddr1 = billAddr1;
    }

    public String getBillAddr2() {
        return billAddr2;
    }

    public void setBillAddr2(String billAddr2) {
        this.billAddr2 = billAddr2;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getBillZip() {
        return billZip;
    }

    public void setBillZip(String billZip) {
        this.billZip = billZip;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}