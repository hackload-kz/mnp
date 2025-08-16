package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.impl;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.ProviderFeignClient;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.request.PlaceRequest;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService {

    private final ProviderFeignClient providerFeignClient;
    public List<SeatResponse> getSeats(Integer page, Integer pageSize) {
        return providerFeignClient.getSeats(page, pageSize)
            .stream()
            .filter(SeatResponse::getIsFree)
            .toList();
    }

    public SeatResponse getSeat(String id) {
        return providerFeignClient.getSeat(id);
    }

    public OrderResponse selectSeat(String id, String orderId) {
        SeatResponse seat = providerFeignClient.getSeat(id);
        if(seat.getIsFree()){
            PlaceRequest placeRequest = PlaceRequest.builder().orderId(orderId).build();
            return providerFeignClient.selectSeat(id,placeRequest);
        }
        return null;
    }

    public OrderResponse releaseSeat(String id) {
        return providerFeignClient.releaseSeat(id);
    }
}
