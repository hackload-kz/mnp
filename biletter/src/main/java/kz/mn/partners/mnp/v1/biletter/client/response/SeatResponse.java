package kz.mn.partners.mnp.v1.biletter.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {

    private String id;
    private Integer row;
    private Integer seat;
    private Boolean isFree;
    private SeatStatus status;
    private BigDecimal price;
    private EventEntity event;

}
