package com.example.redshop.domain;


public class trade {
    private Integer id;
    private Integer buyerId;
    private Integer productId;
    private double trade;
    private String remark;
    private Integer sellerId;
    private Integer product_num;
    private  double total_trade;


    //关联表
    private String productName;
    private String buyerName;
    private String sellerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getbuyerId() {
        return buyerId;
    }

    public void setbuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getproduct_num() {
        return product_num;
    }

    public void setproduct_num(Integer product_num) {
        this.product_num = product_num;
    }

    public Integer getsellerId() {
        return sellerId;
    }

    public void setsellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getproductId() {
        return productId;
    }

    public void setproductId(Integer productId) {
        this.productId = productId;
    }

    public double gettrade() {
        return trade;
    }

    public void settrade(double trade) {
        this.trade = trade;
    }

    public double gettotal_trade() {
        return total_trade;
    }

    public void settotal_trade(double total_trade) {
        this.total_trade = total_trade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public String getbuyerName() {
        return buyerName;
    }

    public void setbuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getsellerName() {
        return sellerName;
    }

    public void setsellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
