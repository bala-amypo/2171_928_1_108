package com.example.demo.model;

public class PriceAdjustmentLog {

    private Long id;
    private Double adjustedPrice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAdjustedPrice() { return adjustedPrice; }
    public void setAdjustedPrice(Double adjustedPrice) { this.adjustedPrice = adjustedPrice; }
}
