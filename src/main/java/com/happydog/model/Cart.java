package com.happydog.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Cart {
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

}
