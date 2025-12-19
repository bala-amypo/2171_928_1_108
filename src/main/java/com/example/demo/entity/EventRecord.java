package com.example.demo.model;

import java.time.LocalDate;

public class EventRecord {

    private Long id;
    private String eventCode;
    private String eventName;
    private double basePrice;
    private boolean active;
    private LocalDate eventDate;   // NEW

    public EventRecord() {}

    public EventRecord(Long id, String eventCode, String eventName,
                       double basePrice, boolean active, LocalDate eventDate) {
        this.id = id;
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.basePrice = basePrice;
        this.active = active;
        this.eventDate = eventDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventCode() { return eventCode; }
    public void setEventCode(String eventCode) { this.eventCode = eventCode; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
}
