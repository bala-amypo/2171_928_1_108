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
        pricingRules.add(rule);
        return rule;
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule updatedRule) {
        Optional<PricingRule> existingRuleOpt = pricingRules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();

        if (existingRuleOpt.isPresent()) {
            PricingRule existingRule = existingRuleOpt.get();
            existingRule.setRuleCode(updatedRule.getRuleCode());
            existingRule.setDescription(updatedRule.getDescription());
            existingRule.setDiscountPercentage(updatedRule.getDiscountPercentage());
            existingRule.setActive(updatedRule.getActive());
            existingRule.setMinRemainingSeats(updatedRule.getMinRemainingSeats());
            existingRule.setMaxRemainingSeats(updatedRule.getMaxRemainingSeats());
            existingRule.setDaysBeforeEvent(updatedRule.getDaysBeforeEvent());
            existingRule.setPriceMultiplier(updatedRule.getPriceMultiplier());
            return existingRule;
        } else {
            throw new IllegalArgumentException("PricingRule not found: " + id);
        }
    }

    @Override
    public List<PricingRule> getActiveRules() {
        List<PricingRule> activeRules = new ArrayList<>();
        for (PricingRule r : pricingRules) {
            if (r.getActive()) {
                activeRules.add(r);
            }
        }
        return activeRules;
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        return pricingRules.stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PricingRule> getAllRules() {
        return new ArrayList<>(pricingRules);
    }
}
