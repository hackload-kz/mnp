package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptDto {
    private String email;
    private String phone;
    private String taxation;
}
