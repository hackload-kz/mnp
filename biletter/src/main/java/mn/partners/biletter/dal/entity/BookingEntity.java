package mn.partners.biletter.dal.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingEntity {
    private Long id;
    private Long eventId;
}


