package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CardEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardInfoDto {
    private String cardMask;
    private CardEnum cardType;
    private String issuingBank;

}
