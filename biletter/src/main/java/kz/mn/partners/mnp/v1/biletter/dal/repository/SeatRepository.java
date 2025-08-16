package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query("""
   SELECT s FROM SeatEntity s
   WHERE s.event.id = :eventId
     AND (:row IS NULL OR s.seatRow = :row)
     AND (:status IS NULL OR s.status = :status)
   ORDER BY s.seatRow ASC, s.number ASC
""")
    Page<SeatEntity> findSeatsByCriteria(
        @Param("eventId") Long eventId,
        @Param("row") Integer row,
        @Param("status") SeatStatus status,
        Pageable pageable
    );
}
