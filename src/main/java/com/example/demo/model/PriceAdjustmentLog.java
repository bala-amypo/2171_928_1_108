package com.example.demo.model;

public class PriceAdjustmentLog {

    private Long eventId;
    private Double oldPrice;
    private Double newPrice;

    public PriceAdjustmentLog() {
    }

    public PriceAdjustmentLog(Long eventId, Double oldPrice, Double newPrice) {
        this.eventId = eventId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }
}
    