package com.example.demo.service;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository repository;

    public PricingRuleServiceImpl(PricingRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PricingRule createRule(PricingRule rule) {
        return repository.save(rule);
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        return repository.findByRuleCode(ruleCode);
    }

    @Override
    public List<PricingRule> getAllRules() {
        return repository.findAll();
    }
}
