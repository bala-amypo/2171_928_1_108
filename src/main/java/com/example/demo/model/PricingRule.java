package com.example.demo.model;

public class PricingRule {

    private String ruleCode;
    private Double priceMultiplier;
    private Integer minRemainingSeats;
    private Integer maxRemainingSeats;
    private Integer daysBeforeEvent;

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public Double getPriceMultiplier() { return priceMultiplier; }
    public void setPriceMultiplier(Double priceMultiplier) { this.priceMultiplier = priceMultiplier; }

    public Integer getMinRemainingSeats() { return minRemainingSeats; }
    public void setMinRemainingSeats(Integer minRemainingSeats) { this.minRemainingSeats = minRemainingSeats; }

    public Integer getMaxRemainingSeats() { return maxRemainingSeats; }
    public void setMaxRemainingSeats(Integer maxRemainingSeats) { this.maxRemainingSeats = maxRemainingSeats; }

    public Integer getDaysBeforeEvent() { return daysBeforeEvent; }
    public void setDaysBeforeEvent(Integer daysBeforeEvent) { this.daysBeforeEvent = daysBeforeEvent; }
}
