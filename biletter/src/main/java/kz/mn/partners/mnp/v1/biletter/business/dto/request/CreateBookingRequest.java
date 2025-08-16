package kz.mn.partners.mnp.v1.biletter.business.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateBookingRequest {
    @NotNull
    private Long eventId;
}
