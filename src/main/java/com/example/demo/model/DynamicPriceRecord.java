package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DynamicPriceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double computedPrice;
    private String appliedRuleCodes;
    private LocalDateTime computedAt;

    public DynamicPriceRecord() {}

    public double getComputedPrice() { return computedPrice; }
    public void setComputedPrice(double computedPrice) { this.computedPrice = computedPrice; }
    public String getAppliedRuleCodes() { return appliedRuleCodes; }
    public void setAppliedRuleCodes(String appliedRuleCodes) { this.appliedRuleCodes = appliedRuleCodes; }
    public LocalDateTime getComputedAt() { return computedAt; }

    @PrePersist
    public void prePersist() { this.computedAt = LocalDateTime.now(); }
}
