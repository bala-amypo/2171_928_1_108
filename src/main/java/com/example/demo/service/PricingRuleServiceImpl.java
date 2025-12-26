package com.example.demo.service;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    @Autowired
    private PricingRuleRepository repository;

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existingRule = repository.findById(id).orElse(null);
        if (existingRule != null) {
            existingRule.setRuleName(rule.getRuleName());
            existingRule.setRuleCode(rule.getRuleCode());
            existingRule.setActive(rule.isActive());
            repository.save(existingRule);
        }
        return existingRule;
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repository.findAll().stream().filter(PricingRule::isActive).toList();
    }
}
