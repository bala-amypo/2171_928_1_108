package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PricingRule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private int minRemainingSeats;
    private int maxRemainingSeats;
    private int daysBeforeEvent;
    private double priceMultiplier;
    private boolean active;

    // getters & setters
}
