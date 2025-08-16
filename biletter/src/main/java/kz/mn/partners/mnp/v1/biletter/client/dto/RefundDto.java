package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundDto {
    private String refundId;
    private PaymentStatus refundStatus;
    private String expectedProcessingTime;
    private String refundMethod;
    private CardInfoDto cardInfo;
}
