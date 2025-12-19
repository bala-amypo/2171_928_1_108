package com.example.demo.service.impl;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PricingRuleServiceImpl implements PricingRuleService {

    private final List<PricingRule> pricingRules = new ArrayList<>();
    private Long nextId = 1L; // auto-increment ID

    @Override
    public PricingRule createRule(PricingRule rule) {
        Optional<PricingRule> existing = pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(rule.getRuleCode()))
                .findFirst();

        if (existing.isPresent()) {
            throw new RuntimeException("Pricing rule with code " + rule.getRuleCode() + " already exists.");
        }

        rule.setId(nextId++);
        pricingRules.add(rule);
        return rule;
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule updatedRule) {
        PricingRule rule = pricingRules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pricing rule not found: " + id));

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
    public List<PricingRule> getActiveRules() {
        List<PricingRule> activeRules = new ArrayList<>();
        for (PricingRule rule : pricingRules) {
            if (rule.getActive()) {
                activeRules.add(rule);
            }
        }
        return activeRules;
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        return pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pricing rule not found: " + ruleCode));
    }

    @Override
    public List<PricingRule> getAllRules() {
        return new ArrayList<>(pricingRules);
    }
}
