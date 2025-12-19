package com.example.demo.service.impl;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PricingRuleServiceImpl implements PricingRuleService {

    private final List<PricingRule> pricingRules = new ArrayList<>();

    @Override
    public PricingRule createRule(PricingRule rule) {
        // Check for duplicate ruleCode
        Optional<PricingRule> existing = pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(rule.getRuleCode()))
                .findFirst();

        if (existing.isPresent()) {
            throw new RuntimeException("Pricing rule with code " + rule.getRuleCode() + " already exists.");
        }

        pricingRules.add(rule);
        return rule;
    }

    @Override
    public List<PricingRule> getAllRules() {
        return new ArrayList<>(pricingRules);
    }

    @Override
    public PricingRule updateRule(String ruleCode, PricingRule updatedRule) {
        Optional<PricingRule> existing = pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst();

        if (existing.isEmpty()) {
            throw new RuntimeException("Pricing rule not found: " + ruleCode);
        }

        PricingRule rule = existing.get();
        rule.setDescription(updatedRule.getDescription());
        rule.setDiscountPercentage(updatedRule.getDiscountPercentage());
        rule.setPriceMultiplier(updatedRule.getPriceMultiplier());
        rule.setMinRemainingSeats(updatedRule.getMinRemainingSeats());
        rule.setMaxRemainingSeats(updatedRule.getMaxRemainingSeats());
        rule.setDaysBeforeEvent(updatedRule.getDaysBeforeEvent());
        rule.setActive(updatedRule.getActive());

        return rule;
    }

    @Override
    public void updateRuleStatus(String ruleCode, boolean active) {
        Optional<PricingRule> existing = pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst();

        if (existing.isEmpty()) {
            throw new RuntimeException("Pricing rule not found: " + ruleCode);
        }

        existing.get().setActive(active);
    }
}
