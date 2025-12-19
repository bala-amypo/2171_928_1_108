package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepository;
    private final SeatInventoryRecordRepository inventoryRepository;
    private final PricingRuleRepository ruleRepository;
    private final DynamicPriceRecordRepository priceRepository;
    private final PriceAdjustmentLogRepository logRepository;

    // ✅ Constructor injection (order MUST match requirement)
    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepository,
            SeatInventoryRecordRepository inventoryRepository,
            PricingRuleRepository ruleRepository,
            DynamicPriceRecordRepository priceRepository,
            PriceAdjustmentLogRepository logRepository
    ) {
        this.eventRepository = eventRepository;
        this.inventoryRepository = inventoryRepository;
        this.ruleRepository = ruleRepository;
        this.priceRepository = priceRepository;
        this.logRepository = logRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event not found"));

        if (!event.getActive()) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new BadRequestException("Seat inventory not found"));

        int remainingSeats = inventory.getRemainingSeats();

        long daysToEvent = ChronoUnit.DAYS.between(
                LocalDate.now(),
                event.getEventDate()
        );

        List<PricingRule> activeRules = ruleRepository.findByActiveTrue();

        List<PricingRule> matchedRules = activeRules.stream()
                .filter(r ->
                        remainingSeats >= r.getMinRemainingSeats()
                                && remainingSeats <= r.getMaxRemainingSeats()
                                && daysToEvent <= r.getDaysBeforeEvent()
                )
                .collect(Collectors.toList());

        double finalPrice = event.getBasePrice();
        String appliedRuleCodes = "";

        if (!matchedRules.isEmpty()) {
            PricingRule rule = matchedRules.get(0);

            if (rule.getPriceMultiplier() <= 0) {
                throw new BadRequestException("Price multiplier must be > 0");
            }

            finalPrice = finalPrice * rule.getPriceMultiplier();
            appliedRuleCodes = rule.getRuleCode();
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setAppliedRuleCodes(appliedRuleCodes);
        record.setComputedAt(LocalDateTime.now());

        return priceRepository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepository.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return priceRepository
                .findFirstByEventIdOrderByComputedAtDesc(eventId)
                .orElse(null);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepository.findAll();
    }
}
