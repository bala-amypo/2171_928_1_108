package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private Double priceMultiplier;
    private Boolean active;

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
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

    // ===== SETTERS =====
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceMultiplier(Double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
