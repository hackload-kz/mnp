package kz.mn.partners.mnp.v1.biletter.business.dto.response;

import lombok.Builder;
import lombok.Data;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;

@Data
@Builder
public class ListSeatsResponseItem {
    private Long id;
    private Long row;
    private Long number;
    private SeatStatus status;
}