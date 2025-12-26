package com.example.demo.model;

public class PricingRule {

    private Long id;
    private boolean active;
    private Double priceMultiplier;
    private Integer minRemainingSeats;
    private Integer maxRemainingSeats;
    private Integer daysBeforeEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(Double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public Integer getMinRemainingSeats() {
        return minRemainingSeats;
    }

    public void setMinRemainingSeats(Integer minRemainingSeats) {
        this.minRemainingSeats = minRemainingSeats;
    }

    public Integer getMaxRemainingSeats() {
        return maxRemainingSeats;
    }

    public void setMaxRemainingSeats(Integer maxRemainingSeats) {
        this.maxRemainingSeats = maxRemainingSeats;
    }

    public Integer getDaysBeforeEvent() {
        return daysBeforeEvent;
    }

    public void setDaysBeforeEvent(Integer daysBeforeEvent) {
        this.daysBeforeEvent = daysBeforeEvent;
    }
}
