package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PlaceRequest {
    private String orderId;
}
