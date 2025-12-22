package com.example.demo.service.impl;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.PricingRuleService;
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
        return repository.save(rule); // ✅ save to DB
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule updatedRule) {
        PricingRule existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setRuleCode(updatedRule.getRuleCode());
            existing.setDescription(updatedRule.getDescription());
            existing.setPriceMultiplier(updatedRule.getPriceMultiplier());
            existing.setActive(updatedRule.isActive());
            return repository.save(existing); // ✅ update DB
        }
        return null;
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        return repository.findAll()
                .stream()
                .filter(rule -> rule.getRuleCode().equals(ruleCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repository.findAll()
                .stream()
                .filter(PricingRule::isActive)
                .toList();
    }

    @Override
    public List<PricingRule> getAllRules() {
        return repository.findAll();
    }
}
