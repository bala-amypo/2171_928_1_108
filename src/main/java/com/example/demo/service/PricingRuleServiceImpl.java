package com.example.demo.service;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    @Autowired
    private PricingRuleRepository pricingRuleRepository;

    @Override
    public PricingRule createRule(PricingRule rule) {
        return pricingRuleRepository.save(rule);
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existingRule = pricingRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingRule not found with id: " + id));

        existingRule.setRuleName(rule.getRuleName());
        existingRule.setRuleValue(rule.getRuleValue());

        return pricingRuleRepository.save(existingRule);
    }

    @Override
    public PricingRule getRuleById(Long id) {
        return pricingRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingRule not found with id: " + id));
    }

    @Override
    public List<PricingRule> getAllRules() {
        return pricingRuleRepository.findAll();
    }

    @Override
    public void deleteRule(Long id) {   // ✅ MISSING METHOD FIXED
        pricingRuleRepository.deleteById(id);
    }
}
