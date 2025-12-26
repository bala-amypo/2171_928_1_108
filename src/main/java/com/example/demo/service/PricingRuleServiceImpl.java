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
    public List<PricingRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        existing.setRuleName(rule.getRuleName());
        existing.setActive(rule.isActive());
        return repository.save(existing);
    }

    @Override
    public void deleteRule(Long id) {
        repository.deleteById(id);
    }
}
