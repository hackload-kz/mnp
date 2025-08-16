package kz.mn.partners.mnp.v1.biletter.business.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
@Jacksonized
public class PaymentNotificationPayload {
    @NotNull
    private String paymentId;
    @NotNull
    private String status;
    @NotNull
    private String teamSlug;
    @NotNull
    private OffsetDateTime timestamp;
    private Map<String, Object> data;
}


