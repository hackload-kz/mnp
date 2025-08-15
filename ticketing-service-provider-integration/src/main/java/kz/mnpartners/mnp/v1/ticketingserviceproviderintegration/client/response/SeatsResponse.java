package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class SeatsResponse {

    private List<SeatResponse> seats;

}
