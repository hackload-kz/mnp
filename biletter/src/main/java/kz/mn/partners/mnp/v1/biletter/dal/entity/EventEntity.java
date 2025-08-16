package kz.mn.partners.mnp.v1.biletter.dal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "datetime_start")
    private LocalDateTime datetimeStart;

    @Column(name = "provider")
    private String provider;

    @Column(name = "is_archive")
    @Builder.Default
    private Boolean isArchive = false;

    @Column(name = "external")
    @Builder.Default
    private Boolean external = false;
}


