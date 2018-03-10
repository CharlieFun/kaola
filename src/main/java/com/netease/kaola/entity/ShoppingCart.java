package com.netease.kaola.entity;

/**
 * Created by funstar on 2018/3/11.
 */
public class ShoppingCart {
    private Long id;
    private Long userId;
    private Long productId;
    private int num;
    private Product product;

    public ShoppingCart() {
    }

    public ShoppingCart(Long userId, Long productId, int num) {
        this.userId = userId;
        this.productId = productId;
        this.num = num;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
