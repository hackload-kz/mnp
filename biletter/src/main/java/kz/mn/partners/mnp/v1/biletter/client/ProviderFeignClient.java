package kz.mn.partners.mnp.v1.biletter.client;


import kz.mn.partners.mnp.v1.biletter.client.response.OrderResponse;
import kz.mn.partners.mnp.v1.biletter.client.response.SeatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.*;

@FeignClient(name = "ProviderFeignClient", url = "${spring.service.payment.url}")
public interface ProviderFeignClient {

    @PostMapping(ORDERS_API_V1_PATH)
    OrderResponse createOrder();

    @GetMapping(ORDERS_API_V1_PATH + GET_PATH)
    OrderResponse getOrder(@PathVariable("id") String id);

    @PatchMapping(ORDERS_API_V1_PATH + ID_PATH + "/submit")
    void submitOrder(@PathVariable("id") String id);

    @PatchMapping(ORDERS_API_V1_PATH + ID_PATH + "/confirm")
    void confirmOrder(@PathVariable("id") String id);

    @PatchMapping(ORDERS_API_V1_PATH + ID_PATH + "/cancel")
    void confirmCancel(@PathVariable("id") String id);

    @GetMapping(PLACES_API_V1_PATH + "/seats")
    List<SeatResponse> getSeats(
        @RequestParam Integer page,
        @RequestParam Integer pageSize);

    @GetMapping(PLACES_API_V1_PATH + ID_PATH)
    SeatResponse getSeat(@PathVariable("id") String id);

    @PatchMapping(PLACES_API_V1_PATH + ID_PATH + "/select")
    OrderResponse selectSeat(@PathVariable("id") String id, @RequestParam String orderId);

    @PatchMapping(PLACES_API_V1_PATH + ID_PATH + "/release")
    OrderResponse releaseSeat(@PathVariable("id") String id);

}
