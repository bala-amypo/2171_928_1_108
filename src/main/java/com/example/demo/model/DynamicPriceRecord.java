package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DynamicPriceRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Long eventId;
    private Double computedPrice;
    private String appliedRuleCodes;
    private LocalDateTime computedAt;

    @PrePersist
    public void prePersist() {
        computedAt = LocalDateTime.now();
    }

    // getters & setters
}
