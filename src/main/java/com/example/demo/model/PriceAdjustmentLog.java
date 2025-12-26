package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PriceAdjustmentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long eventId;
    private double oldPrice;
    private double newPrice;
    private LocalDateTime changedAt;

    public PriceAdjustmentLog() {}

    public long getEventId() { return eventId; }
    public void setEventId(long eventId) { this.eventId = eventId; }
    public double getOldPrice() { return oldPrice; }
    public void setOldPrice(double oldPrice) { this.oldPrice = oldPrice; }
    public double getNewPrice() { return newPrice; }
    public void setNewPrice(double newPrice) { this.newPrice = newPrice; }
    public LocalDateTime getChangedAt() { return changedAt; }

    @PrePersist
    public void prePersist() { this.changedAt = LocalDateTime.now(); }
}
