package kz.mn.partners.mnp.v1.biletter.dal.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingEntity {
    private Long id;
    private Long eventId;
}


