package com.netease.kaola.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * Created by funstar on 2018/3/8.
 */
public class Orderdetail {
    private Long id;
    private Long orderId;
    private Long productId;
    //商品数量
    private int amount;
    //购买时间
    private Timestamp createtime;
    //格式化的日期
    private String stringTime;
    //购买时的商品价格
    private Double currentPrice;
    private Product product;

    public Orderdetail() {
    }

    public Orderdetail(Long orderId, Long productId, int amount, Double currentPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
        setStringTime(createtime);
    }

    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime(Timestamp createtime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime = dateFormat.format(createtime);//将时间格式转换成符合Timestamp要求的格式.
        this.stringTime = newTime;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
