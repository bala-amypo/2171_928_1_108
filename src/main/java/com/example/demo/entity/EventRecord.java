package com.example.demo.model;

import java.time.LocalDate;

public class EventRecord {
    private Long id;
    private String eventCode;
    private String name;
    private LocalDate eventDate;
    private double basePrice;
    private int totalSeats;
    private int remainingSeats;
    private boolean active;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventCode() { return eventCode; }
    public void setEventCode(String eventCode) { this.eventCode = eventCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getRemainingSeats() { return remainingSeats; }
    public void setRemainingSeats(int remainingSeats) { this.remainingSeats = remainingSeats; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
