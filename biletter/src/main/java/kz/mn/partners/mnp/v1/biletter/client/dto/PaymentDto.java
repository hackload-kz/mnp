package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.Currency;
import kz.mn.partners.mnp.v1.biletter.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {

    private String paymentId;
    private String orderId;
    private PaymentStatus status;
    private String statusDescription;
    private Integer amount;
    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresAt;
    private String description;
    private Short payType;
}
