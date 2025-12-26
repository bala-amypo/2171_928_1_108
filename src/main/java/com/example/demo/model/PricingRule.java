package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PricingRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private double priceMultiplier;
    private int minRemainingSeats;
    private int maxRemainingSeats;
    private int daysBeforeEvent;

    public PricingRule() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public double getPriceMultiplier() { return priceMultiplier; }
    public void setPriceMultiplier(double priceMultiplier) { this.priceMultiplier = priceMultiplier; }
    public int getMinRemainingSeats() { return minRemainingSeats; }
    public void setMinRemainingSeats(int minRemainingSeats) { this.minRemainingSeats = minRemainingSeats; }
    public int getMaxRemainingSeats() { return maxRemainingSeats; }
    public void setMaxRemainingSeats(int maxRemainingSeats) { this.maxRemainingSeats = maxRemainingSeats; }
    public int getDaysBeforeEvent() { return daysBeforeEvent; }
    public void setDaysBeforeEvent(int daysBeforeEvent) { this.daysBeforeEvent = daysBeforeEvent; }
}
