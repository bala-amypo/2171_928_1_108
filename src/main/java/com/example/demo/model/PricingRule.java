package com.example.demo.service;

import com.example.demo.model.PricingRule;

import java.util.List;

public interface PricingRuleService {
    PricingRule updateRule(Long id, PricingRule rule);
    List<PricingRule> getActiveRules();
}
