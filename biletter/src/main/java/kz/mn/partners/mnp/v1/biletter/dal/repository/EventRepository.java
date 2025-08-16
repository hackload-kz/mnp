package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository  extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM events e WHERE e.datetime_start::date = :date",
        nativeQuery = true) //todo дописать запрос с использованием query
    List<EventEntity> getByFilter(LocalDate date, String query);
}
