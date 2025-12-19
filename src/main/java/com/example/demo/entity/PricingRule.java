package com.example.demo.model;

public class PricingRule {
    private String code;
    private boolean active;
    private int minRemainingSeats;
    private int maxRemainingSeats;
    private int daysBeforeEvent;
    private double priceMultiplier;

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public int getMinRemainingSeats() { return minRemainingSeats; }
    public void setMinRemainingSeats(int minRemainingSeats) { this.minRemainingSeats = minRemainingSeats; }
    public int getMaxRemainingSeats() { return maxRemainingSeats; }
    public void setMaxRemainingSeats(int maxRemainingSeats) { this.maxRemainingSeats = maxRemainingSeats; }
    public int getDaysBeforeEvent() { return daysBeforeEvent; }
    public void setDaysBeforeEvent(int daysBeforeEvent) { this.daysBeforeEvent = daysBeforeEvent; }
    public double getPriceMultiplier() { return priceMultiplier; }
    public void setPriceMultiplier(double priceMultiplier) { this.priceMultiplier = priceMultiplier; }
}
