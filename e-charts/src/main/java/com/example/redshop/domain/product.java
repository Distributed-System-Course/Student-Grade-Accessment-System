package com.example.redshop.domain;


public class product {
    private int id;
    private String name;
    private int sellerId;
    private String produce_date;
    private int selectedNum = 0;
    private int maxNum = 50;
    private double price;
    private String info;

    public String getProduce_date(){return produce_date;}

    public void setProduce_date(String produce_date_) {this.produce_date = produce_date_;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() { return this.price;}

    public void setPrice(double price_) {this.price = price_;}

    public int getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        this.selectedNum = selectedNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
