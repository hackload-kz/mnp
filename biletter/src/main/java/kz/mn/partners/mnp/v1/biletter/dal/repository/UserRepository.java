package kz.mn.partners.mnp.v1.biletter.dal.repository;

import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);

    List<UserEntity> findByFirstNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(
            String firstName, String surname);

    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);

    List<UserEntity> findBySurnameContainingIgnoreCase(String surname);

    List<UserEntity> findByIsActive(Boolean isActive);
}
