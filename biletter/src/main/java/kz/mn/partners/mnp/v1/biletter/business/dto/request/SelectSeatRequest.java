package kz.mn.partners.mnp.v1.biletter.business.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class SelectSeatRequest {
    private Long bookingId;
    private Long seatId;
}