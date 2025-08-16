package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {

    private String id;
    private Integer row;
    private Integer seat;
    private Boolean isFree;

}
