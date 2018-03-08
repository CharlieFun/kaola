package com.netease.kaola.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by funstar on 2018/3/8.
 */
public class Order {
    private Long id;
    private Long userId;
    private Timestamp createtime;

    //订单明细
    private List<Orderdetail> orderdetails;

    public Order() {
    }

    public Order(Long userId, Timestamp createtime) {
        this.userId = userId;
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public List<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
