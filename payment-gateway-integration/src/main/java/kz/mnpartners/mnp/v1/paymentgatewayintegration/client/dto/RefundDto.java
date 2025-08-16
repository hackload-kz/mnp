package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundDto {
    private String refundId;
    private PaymentStatusEnum refundStatus;
    private String expectedProcessingTime;
    private String refundMethod;
    private CardInfoDto cardInfo;
}
