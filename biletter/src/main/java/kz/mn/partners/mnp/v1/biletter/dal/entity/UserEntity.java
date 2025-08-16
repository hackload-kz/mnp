package kz.mn.partners.mnp.v1.biletter.dal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;

    @Column(name = "password_plain")
    private String passwordPlain;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "last_logged_in", nullable = false)
    private LocalDateTime lastLoggedIn;
}
