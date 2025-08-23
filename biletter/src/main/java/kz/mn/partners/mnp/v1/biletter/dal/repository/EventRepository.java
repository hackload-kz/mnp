package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventRepository  extends JpaRepository<EventEntity, Long> {
    @Query(
        value = "SELECT e.* FROM events e " +
            "WHERE e.datetime_start >= :dateFrom AND e.datetime_start < :dateTo",
        nativeQuery = true
    )
    Page<EventEntity> findByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);

    @Query(
        value = "SELECT e.* FROM events e " +
            "WHERE to_tsquery('russian', :searchQuery) @@ to_tsvector('russian', e.title || ' ' || e.DESCription)",
        nativeQuery = true
    )
    Page<EventEntity> findBySearchQuery(String searchQuery, Pageable pageable);

    @Query(
        value = "SELECT e.* FROM events e " +
            "WHERE e.datetime_start >= :dateFrom AND e.datetime_start < :dateTo AND " +
            "to_tsquery('russian', :searchQuery) @@ to_tsvector('russian', e.title || ' ' || e.DESCription)",
        nativeQuery = true
    )
    Page<EventEntity> findByDateAndSearchQuery(LocalDateTime dateFrom, LocalDateTime dateTo, String searchQuery, Pageable pageable);

    Optional<EventEntity> findFirstByExternalIs(Boolean external);
}
