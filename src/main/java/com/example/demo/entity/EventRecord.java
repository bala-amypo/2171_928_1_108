package com.example.demo.model;

import java.time.LocalDate;

public class EventRecord {
    private Long id;
    private String eventCode;
    private LocalDate eventDate;
    private int totalSeats;
    private int remainingSeats;
    private boolean active;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEventCode() { return eventCode; }
    public void setEventCode(String eventCode) { this.eventCode = eventCode; }
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getRemainingSeats() { return remainingSeats; }
    public void setRemainingSeats(int remainingSeats) { this.remainingSeats = remainingSeats; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
