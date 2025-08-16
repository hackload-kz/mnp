package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeesDto {
    private Integer processingFee;
    private Integer totalFees;
    private CurrencyEnum feeCurrency;

}
