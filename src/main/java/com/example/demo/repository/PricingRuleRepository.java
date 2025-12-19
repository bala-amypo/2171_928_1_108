public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    boolean existsByRuleCode(String code);
    List<PricingRule> findByActiveTrue();
}
