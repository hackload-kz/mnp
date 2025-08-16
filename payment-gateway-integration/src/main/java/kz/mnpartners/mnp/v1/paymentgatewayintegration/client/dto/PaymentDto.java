package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {

    private String paymentId;
    private String orderId;
    private PaymentStatusEnum status;
    private String statusDescription;
    private Integer amount;
    private CurrencyEnum currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresAt;
    private String description;
    private Short payType;
}
