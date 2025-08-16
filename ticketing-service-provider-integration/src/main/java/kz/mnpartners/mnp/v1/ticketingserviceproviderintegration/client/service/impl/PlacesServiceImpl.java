package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.service.impl;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.ProviderFeignClient;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService {

    private final ProviderFeignClient providerFeignClient;
    private final PlacesService placesService;
    public List<SeatResponse> getSeats(Integer page, Integer pageSize) {
        return providerFeignClient.getSeats(page, pageSize);
    }

    public SeatResponse getSeat(String id) {
        return providerFeignClient.getSeat(id);
    }

    public OrderResponse selectSeat(String id) {
        SeatResponse seat = placesService.getSeat(id);
        if(seat.getIsFree()){
            return providerFeignClient.selectSeat(id);
        }
        return null;
    }

    public OrderResponse releaseSeat(String id) {
        return providerFeignClient.releaseSeat(id);
    }
}
