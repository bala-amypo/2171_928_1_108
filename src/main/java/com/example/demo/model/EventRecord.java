package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class EventRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventCode;

    private Double basePrice;

    private LocalDate eventDate;

    private boolean active;

    // ✅ REQUIRED getters
    public Long getId() {
        return id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public boolean isActive() {
        return active;
    }

    // setters (needed for JPA + controllers)
    public void setId(Long id) {
        this.id = id;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
