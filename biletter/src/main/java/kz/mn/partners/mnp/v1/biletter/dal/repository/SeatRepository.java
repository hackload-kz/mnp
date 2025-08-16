package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    
    @Query("SELECT s FROM SeatEntity s WHERE s.event.id = :eventId " +
           "AND (:row IS NULL OR s.seatRow = :row) " +
           "AND (:status IS NULL OR s.status = :status)")
    Page<SeatEntity> findSeatsByCriteria(@Param("eventId") Long eventId,
                                        @Param("row") Integer row,
                                        @Param("status") SeatStatus status,
                                        Pageable pageable);
    

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SeatEntity s WHERE s.id = :id")
    Optional<SeatEntity> findByIdWithLock(@Param("id") Long id);
}
