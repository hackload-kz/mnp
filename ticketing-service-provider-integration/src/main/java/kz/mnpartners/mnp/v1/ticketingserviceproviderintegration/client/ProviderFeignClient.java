package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ProviderFeignClient", url = "${app.base-url.event-provider}/api/partners/v1/")
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

    @PatchMapping("places/{id}/select")
    OrderResponse selectSeat(@PathVariable("id") String id);

    @PatchMapping("places/{id}/release")
    OrderResponse releaseSeat(@PathVariable("id") String id);
}
