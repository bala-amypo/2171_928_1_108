public interface SeatInventoryRecordRepository extends JpaRepository<SeatInventoryRecord, Long> {
    Optional<SeatInventoryRecord> findByEventId(Long eventId);
}
