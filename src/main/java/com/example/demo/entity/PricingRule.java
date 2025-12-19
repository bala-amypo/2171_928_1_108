public class PricingRule {
    private String ruleCode;
    private int minRemainingSeats;
    private int maxRemainingSeats;
    private int daysBeforeEvent;
    private double priceMultiplier;

    // Getters
    public String getRuleCode() { return ruleCode; }
    public int getMinRemainingSeats() { return minRemainingSeats; }
    public int getMaxRemainingSeats() { return maxRemainingSeats; }
    public int getDaysBeforeEvent() { return daysBeforeEvent; }
    public double getPriceMultiplier() { return priceMultiplier; }

    // Setters
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setMinRemainingSeats(int minRemainingSeats) { this.minRemainingSeats = minRemainingSeats; }
    public void setMaxRemainingSeats(int maxRemainingSeats) { this.maxRemainingSeats = maxRemainingSeats; }
    public void setDaysBeforeEvent(int daysBeforeEvent) { this.daysBeforeEvent = daysBeforeEvent; }
    public void setPriceMultiplier(double priceMultiplier) { this.priceMultiplier = priceMultiplier; }
}
