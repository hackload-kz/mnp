package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class SeatResponse {

    private String id;
    private Integer row;
    private Integer seat;
    private Boolean isFree;

}
