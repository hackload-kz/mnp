package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;

@Data
@Builder
@Jacksonized
public class OrderResponse {

  private Long id;
  private Long orderId;
  private String status ;
  private Timestamp startedAt;
  private Timestamp updatedAt;
  private Integer placesCount;
}
