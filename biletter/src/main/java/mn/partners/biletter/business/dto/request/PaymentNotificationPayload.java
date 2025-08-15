package mn.partners.biletter.business.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
@Jacksonized
public class PaymentNotificationPayload {
    private String paymentId;
    private String status;
    private String teamSlug;
    private OffsetDateTime timestamp;
    private Map<String, Object> data;
}


