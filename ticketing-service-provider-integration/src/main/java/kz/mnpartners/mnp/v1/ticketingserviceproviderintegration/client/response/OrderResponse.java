package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;

@Data
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

  private Long id;
  private String orderId;
  private OrderStatusEnum status ;
  private Timestamp startedAt;
  private Timestamp updatedAt;
  private Integer placesCount;
}
