package com.example.redshop.domain;


public class Attendance {
    private Integer id;
    private Integer productId;
    private Integer buyerId;
    private String type;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getproductId() {
        return productId;
    }

    public void setproductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getbuyerId() {
        return buyerId;
    }

    public void setbuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
