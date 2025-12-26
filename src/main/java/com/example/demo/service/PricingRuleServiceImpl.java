package com.example.demo.service.impl;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.PricingRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    @Autowired
    private PricingRuleRepository repository;

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setRuleCode(rule.getRuleCode());
            existing.setRuleName(rule.getRuleName());
            existing.setActive(rule.isActive());
            repository.save(existing);
        }
        return existing;
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repository.findAll().stream().filter(PricingRule::isActive).toList();
    }
}
