package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DynamicPriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private double computedPrice;
    private String appliedRuleCodes;
    private LocalDateTime computedAt = LocalDateTime.now();

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setComputedPrice(double computedPrice) {
        this.computedPrice = computedPrice;
    }

    public void setAppliedRuleCodes(String appliedRuleCodes) {
        this.appliedRuleCodes = appliedRuleCodes;
    }
}
