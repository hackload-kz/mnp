package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CardEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardInfoDto {
    private String cardMask;
    private CardEnum cardType;
    private String issuingBank;

}
