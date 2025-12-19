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
        return repository.save(rule);
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        existing.setRuleCode(rule.getRuleCode());
        existing.setPriceMultiplier(rule.getPriceMultiplier());
        existing.setMinRemainingSeats(rule.getMinRemainingSeats());
        existing.setMaxRemainingSeats(rule.getMaxRemainingSeats());
        existing.setDaysBeforeEvent(rule.getDaysBeforeEvent());
        existing.setActive(rule.getActive());

        return repository.save(existing);
    }

    @Override
    public PricingRule getRuleByCode(String ruleCode) {
        return repository.findAll().stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repository.getActiveRules();
    }

    @Override
    public List<PricingRule> getAllRules() {
        return repository.findAll();
    }
}
