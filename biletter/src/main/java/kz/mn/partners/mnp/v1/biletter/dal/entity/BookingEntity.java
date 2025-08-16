package kz.mn.partners.mnp.v1.biletter.dal.entity;

import jakarta.persistence.*;
import kz.mn.partners.mnp.v1.biletter.common.model.BookingStatus;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private BookingStatus status = BookingStatus.CREATED;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "booking_seat",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    @Builder.Default
    private Set<SeatEntity> seats = new HashSet<>();
}


