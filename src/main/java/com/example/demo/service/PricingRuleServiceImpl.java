package com.example.demo.service.impl;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;
import org.springframework.stereotype.Service;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    @Override
    public PricingRule getRuleByCode(String code) {
        PricingRule rule = new PricingRule();
        rule.setRuleCode(code);
        rule.setPriceMultiplier(1.0);
        rule.setMinRemainingSeats(0);
        rule.setMaxRemainingSeats(100);
        rule.setDaysBeforeEvent(0);
        return rule;
    }
}
