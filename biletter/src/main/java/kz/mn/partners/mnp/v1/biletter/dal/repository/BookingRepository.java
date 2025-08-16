package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUser(UserEntity user);
}
