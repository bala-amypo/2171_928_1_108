package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EventRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventCode;
    private String eventName;
    private boolean active;

    public Long getId() { return id; }

    public String getEventCode() { return eventCode; }
    public void setEventCode(String eventCode) { this.eventCode = eventCode; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
