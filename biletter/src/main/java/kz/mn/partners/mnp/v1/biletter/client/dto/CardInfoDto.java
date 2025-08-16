package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardInfoDto {
    private String cardMask;
    private Card cardType;
    private String issuingBank;

}
