package com.netease.kaola.entity;

/**
 * Created by funstar on 2018/1/25.
 */
public class Product {
    private Long id;
    //标题
    private String title;
    //摘要
    private String summary;
    //正文
    private String detail;
    //价格
    private Double price;
    //图片
    private byte[] imgData;
    //是否上架
    private boolean status;
    //上次购买的价格，这是根据不同用户ID进行查询的
    private Double lastBuyPrice = 0.0;
    //是否被购买，由seller查看
    private boolean haveBuy = false;
    //被购买的总数量
    private int totalBuyNum = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }

    public Double getLastBuyPrice() {
        return lastBuyPrice;
    }

    public void setLastBuyPrice(Double lastBuyPrice) {
        this.lastBuyPrice = lastBuyPrice;
    }

    public boolean isHaveBuy() {
        return haveBuy;
    }

    public void setHaveBuy(boolean haveBuy) {
        this.haveBuy = haveBuy;
    }

    public int getTotalBuyNum() {
        return totalBuyNum;
    }

    public void setTotalBuyNum(int totalBuyNum) {
        this.totalBuyNum = totalBuyNum;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", status=" + status +
                "}";
    }
}
