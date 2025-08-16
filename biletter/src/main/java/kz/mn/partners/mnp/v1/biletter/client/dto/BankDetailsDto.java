package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDetailsDto {
    private String bankTransactionId;
    private String authorizationCode;
    private Integer rrn;
    private String responseCode;
    private String responseMessage;
    private String originalAuthorizationCode;
    private String cancellationAuthorizationCode;
}
