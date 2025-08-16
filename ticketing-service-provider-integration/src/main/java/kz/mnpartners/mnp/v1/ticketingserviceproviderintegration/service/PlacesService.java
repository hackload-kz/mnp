package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;

import java.util.List;

public interface PlacesService {

    List<SeatResponse> getSeats(Integer page, Integer pageSize);

    SeatResponse getSeat(String id);

    OrderResponse selectSeat(String id);

    OrderResponse releaseSeat(String id);
}
