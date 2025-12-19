public interface EventRecordRepository extends JpaRepository<EventRecord, Long> {
    boolean existsByEventCode(String code);
    Optional<EventRecord> findByEventCode(String code);
}
