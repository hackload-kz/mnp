package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatsResponse {

    private List<SeatResponse> seats;

}
