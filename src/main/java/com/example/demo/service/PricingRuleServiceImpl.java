package com.example.demo.service;

import com.example.demo.model.PricingRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    private final List<PricingRule> rules = new ArrayList<>();

    @Override
    public PricingRule createRule(PricingRule rule) {
        rules.add(rule);
        return rule;
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule updatedRule) {
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getId().equals(id)) {
                rules.set(i, updatedRule);
                return updatedRule;
            }
        }
        return null;
    }

    @Override
    public List<PricingRule> getActiveRules() {
        List<PricingRule> activeRules = new ArrayList<>();
        for (PricingRule rule : rules) {
            if (rule.getActive()) {   // ✅ FIXED
                activeRules.add(rule);
            }
        }
        return activeRules;
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        Optional<PricingRule> rule = rules.stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst();
        return rule.orElse(null);
    }

    @Override
    public List<PricingRule> getAllRules() {
        return rules;
    }
}
