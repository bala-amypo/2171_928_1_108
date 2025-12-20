package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_record")
public class EventRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eventCode;

    private String eventName;

    private boolean active;

    public EventRecord() {}

    public EventRecord(String eventCode, String eventName, boolean active) {
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventCode() { return eventCode; }
    public void setEventCode(String eventCode) { this.eventCode = eventCode; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
