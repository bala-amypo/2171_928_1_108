package com.example.demo.service;

import com.example.demo.model.PricingRule;
import java.util.List;

public interface PricingRuleService {

    // Existing methods
    PricingRule updateRule(Long id, PricingRule rule);
    List<PricingRule> getActiveRules();

    // New methods required by controller
    PricingRule createRule(PricingRule rule);
    PricingRule getRuleByCode(String ruleCode);
    List<PricingRule> getAllRules();
}
