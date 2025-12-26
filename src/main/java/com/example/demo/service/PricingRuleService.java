package com.example.demo.service;

import com.example.demo.model.PricingRule;

public interface PricingRuleService {
    PricingRule getRuleByCode(String code);
}
