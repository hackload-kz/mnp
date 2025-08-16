package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDto {
    private String email;
    private String phone;
    private String taxation;
}
