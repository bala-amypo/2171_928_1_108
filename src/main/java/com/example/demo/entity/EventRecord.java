public class EventRecord {
    private Long id;
    private LocalDate eventDate; // make sure this exists
    private Double basePrice;
    private Boolean active;

    // Add getters and setters for all fields
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
