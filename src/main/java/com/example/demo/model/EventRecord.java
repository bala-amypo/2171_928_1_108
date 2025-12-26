package com.example.demo.model;

import java.time.LocalDate;

public class EventRecord {

    private Long id;
    private Double basePrice;
    private LocalDate eventDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
}
