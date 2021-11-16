package com.example.redshop.domain;


public class tradeStats3 {
    private Double max_trade;
    private Double avg_trade;
    private Double min_trade;
    private String productName;
    private String sellerName;
    private String buyerName;

    public Double getMax_trade() {
        return max_trade;
    }

    public void setMax_trade(Double max_trade) {
        this.max_trade = max_trade;
    }

    public Double getAvg_trade() {
        return avg_trade;
    }

    public void setAvg_trade(Double avg_trade) {
        this.avg_trade = avg_trade;
    }

    public Double getMin_trade() {
        return min_trade;
    }

    public void setMin_trade(Double min_trade) {
        this.min_trade = min_trade;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public String getsellerName() { return sellerName;
    }

    public void setsellerName(String sellername) { this.sellerName = sellername;
    }

    public String getbuyerName() { return buyerName;
    }

    public void setbuyerName(String buyername) { this.buyerName = buyername;
    }
}
