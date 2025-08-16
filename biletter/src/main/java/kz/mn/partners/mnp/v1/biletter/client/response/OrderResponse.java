package kz.mn.partners.mnp.v1.biletter.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.common.model.OrderStatus;
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
  private OrderStatus status ;
  private Timestamp startedAt;
  private Timestamp updatedAt;
  private Integer placesCount;
}
