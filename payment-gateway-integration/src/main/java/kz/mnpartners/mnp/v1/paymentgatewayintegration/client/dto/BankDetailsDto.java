package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDetailsDto {
    private String bankTransactionId;
    private String authorizationCode;
    private Integer rrn;
    private String responseCode;
    private String responseMessage;
    private String originalAuthorizationCode;
    private String cancellationAuthorizationCode;
}
