public class DynamicPriceRecord {
    private Long eventId;
    private double computedPrice;
    private String appliedRuleCodes;
    private LocalDateTime computedAt;

    // Getters and setters
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public double getComputedPrice() { return computedPrice; }
    public void setComputedPrice(double computedPrice) { this.computedPrice = computedPrice; }

    public String getAppliedRuleCodes() { return appliedRuleCodes; }
    public void setAppliedRuleCodes(String appliedRuleCodes) { this.appliedRuleCodes = appliedRuleCodes; }

    public LocalDateTime getComputedAt() { return computedAt; }
    public void setComputedAt(LocalDateTime computedAt) { this.computedAt = computedAt; }
}
