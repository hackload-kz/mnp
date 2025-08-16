package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto {
    private String externalRequestId;
    private String confirmationReason;
    private String merchantReference;
}
