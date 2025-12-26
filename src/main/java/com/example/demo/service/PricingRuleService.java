package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PricingRule;

import java.util.List;
import java.util.Optional;

public interface PricingRuleService {

    PricingRule createRule(PricingRule rule) throws BadRequestException;

    Optional<PricingRule> getRuleByCode(String ruleCode);

    List<PricingRule> getAllRules();

    List<PricingRule> getActiveRules();
}
