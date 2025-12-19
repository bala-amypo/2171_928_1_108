package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double priceMultiplier;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPriceMultiplier() {
        return priceMultiplier;
    }

    public Boolean getActive() {
        return active;
    }
}
