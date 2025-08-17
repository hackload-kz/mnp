package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.request.PlaceRequest;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.config.feign.FeignRetryConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ProviderFeignClient", url = "${spring.service.provider.url}/api/partners/v1/", configuration = FeignRetryConfig.class)
public interface ProviderFeignClient {

    @PostMapping("orders")
    OrderResponse createOrder();

    @GetMapping("orders/{id}")
    OrderResponse getOrder(@PathVariable("id") String id);

    @PatchMapping("orders/{id}/submit")
    void submitOrder(@PathVariable("id") String id);

    @PatchMapping("orders/{id}/confirm")
    void confirmOrder(@PathVariable("id") String id);

    @PatchMapping("orders/{id}/cancel")
    void confirmCancel(@PathVariable("id") String id);

    @GetMapping("places?page={page}&pageSize={pageSize}")
    List<SeatResponse> getSeats(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);

    @GetMapping("places/{id}")
    SeatResponse getSeat(@PathVariable("id") String id);

    @PatchMapping(value = "places/{id}/select",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderResponse selectSeat(@PathVariable("id") String id, @RequestBody PlaceRequest placeRequest);

    @PatchMapping("places/{id}/release")
    OrderResponse releaseSeat(@PathVariable("id") String id);
}
