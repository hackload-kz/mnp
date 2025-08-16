package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query(value = """
            SELECT s.* FROM seat s
            WHERE s.event_id = :eventId
            AND (:row IS NULL OR s.seat_row = :row)
            AND (:status IS NULL OR s.status = :status)
            ORDER BY s.seat_row ASC, s.number ASC
            LIMIT :pageSize OFFSET :offset
            """, nativeQuery = true)
    List<SeatEntity> findSeatsByCriteria(
            @Param("eventId") Long eventId,
            @Param("pageSize") Long pageSize,
            @Param("row") Long row,
            @Param("status") String status,
            @Param("offset") Long offset
    );
}
