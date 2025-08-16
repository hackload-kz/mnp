package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeesDto {
    private Integer processingFee;
    private Integer totalFees;
    private Currency feeCurrency;

}
