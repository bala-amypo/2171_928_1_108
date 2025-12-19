package com.example.demo.model;

public class PricingRule {

    private String ruleCode;
    private String description;
    private double discountPercentage;
    private double priceMultiplier;      // NEW
    private int minRemainingSeats;       // NEW
    private int maxRemainingSeats;       // NEW
    private int daysBeforeEvent;         // NEW
    private boolean active;

    public PricingRule() {}

    public PricingRule(String ruleCode, String description, double discountPercentage,
                       double priceMultiplier, int minRemainingSeats, int maxRemainingSeats,
                       int daysBeforeEvent, boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.priceMultiplier = priceMultiplier;
        this.minRemainingSeats = minRemainingSeats;
        this.maxRemainingSeats = maxRemainingSeats;
        this.daysBeforeEvent = daysBeforeEvent;
        this.active = active;
    }

    // Getters and Setters
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    public double getPriceMultiplier() { return priceMultiplier; }
    public void setPriceMultiplier(double priceMultiplier) { this.priceMultiplier = priceMultiplier; }

    public int getMinRemainingSeats() { return minRemainingSeats; }
    public void setMinRemainingSeats(int minRemainingSeats) { this.minRemainingSeats = minRemainingSeats; }

    public int getMaxRemainingSeats() { return maxRemainingSeats; }
    public void setMaxRemainingSeats(int maxRemainingSeats) { this.maxRemainingSeats = maxRemainingSeats; }

    public int getDaysBeforeEvent() { return daysBeforeEvent; }
    public void setDaysBeforeEvent(int daysBeforeEvent) { this.daysBeforeEvent = daysBeforeEvent; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
