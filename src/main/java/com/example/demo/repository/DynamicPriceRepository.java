public interface DynamicPriceRecordRepository extends JpaRepository<DynamicPriceRecord, Long> {
    List<DynamicPriceRecord> findByEventIdOrderByComputedAtDesc(Long eventId);
    DynamicPriceRecord findFirstByEventIdOrderByComputedAtDesc(Long eventId);
}
