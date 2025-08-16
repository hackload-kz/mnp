package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeesDto {
    private Integer processingFee;
    private Integer totalFees;
    private CurrencyEnum feeCurrency;

}
