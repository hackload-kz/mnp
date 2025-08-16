package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SettlementDto {
    private LocalDateTime settlementDate;
    private Integer settlementAmount;
    private CurrencyEnum settlementCurrency;
}
