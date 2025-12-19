package com.example.demo.model;

public class PricingRule {

    private String ruleCode;
    private String description;
    private double discountPercentage;
    private boolean active;

    // Default constructor
    public PricingRule() {}

    // Parameterized constructor
    public PricingRule(String ruleCode, String description, double discountPercentage, boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.active = active;
    }

    // Getters and Setters
    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
