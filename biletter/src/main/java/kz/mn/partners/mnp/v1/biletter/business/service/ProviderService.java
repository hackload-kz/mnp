package kz.mn.partners.mnp.v1.biletter.business.service;


import kz.mn.partners.mnp.v1.biletter.client.ProviderFeignClient;
import kz.mn.partners.mnp.v1.biletter.client.response.OrderResponse;
import kz.mn.partners.mnp.v1.biletter.client.response.SeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderFeignClient providerFeignClient;
    private final SeatService seatService;
    private final EventService eventService;

    public OrderResponse createOrder() {
        return providerFeignClient.createOrder();
    }

    public OrderResponse getOrder(String id) {
        return providerFeignClient.getOrder(id);
    }

    public void submitOrder(String id) {
        providerFeignClient.submitOrder(id);
    }

    public void confirmOrder(String id) {
        providerFeignClient.confirmOrder(id);
    }

    public void confirmCancel(String id) {
        providerFeignClient.confirmCancel(id);
    }

    public List<SeatResponse> getSeats(Integer page, Integer pageSize) {
        return providerFeignClient.getSeats(page, pageSize);
    }

    public SeatResponse getSeat(String id) {
        return providerFeignClient.getSeat(id);
    }

    public OrderResponse selectSeat(String id, String orderId) {
        return providerFeignClient.selectSeat(id, orderId);
    }

    public OrderResponse releaseSeat(String id) {
        return providerFeignClient.releaseSeat(id);
    }

}
