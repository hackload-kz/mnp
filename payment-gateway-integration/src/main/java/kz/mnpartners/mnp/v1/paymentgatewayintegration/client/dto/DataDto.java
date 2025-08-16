package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataDto {
    private String externalRequestId;
    private String confirmationReason;
    private String merchantReference;
}
