package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;

    private Double priceMultiplier;

    private Integer minRemainingSeats;

    private Integer maxRemainingSeats;

    private Integer daysBeforeEvent;

    private boolean active;

    // ✅ REQUIRED getters
    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public Double getPriceMultiplier() {
        return priceMultiplier;
    }

    public Integer getMinRemainingSeats() {
        return minRemainingSeats;
    }

    public Integer getMaxRemainingSeats() {
        return maxRemainingSeats;
    }

    public Integer getDaysBeforeEvent() {
        return daysBeforeEvent;
    }

    public boolean isActive() {
        return active;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public void setPriceMultiplier(Double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public void setMinRemainingSeats(Integer minRemainingSeats) {
        this.minRemainingSeats = minRemainingSeats;
    }

    public void setMaxRemainingSeats(Integer maxRemainingSeats) {
        this.maxRemainingSeats = maxRemainingSeats;
    }

    public void setDaysBeforeEvent(Integer daysBeforeEvent) {
        this.daysBeforeEvent = daysBeforeEvent;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
