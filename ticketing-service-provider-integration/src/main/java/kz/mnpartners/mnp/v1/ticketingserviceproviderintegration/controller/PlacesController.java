package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.controller;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.SeatResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.common.constant.Constants.ID_PATH;
import static kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.common.constant.Constants.PLACES_API_V1_PATH;

@RestController
@RequestMapping(value = PLACES_API_V1_PATH)
@RequiredArgsConstructor
public class PlacesController {

    private final PlacesService placesService;

    @GetMapping("/seats")
    private List<SeatResponse> getSeats(
        @RequestParam Integer page,
        @RequestParam Integer pageSize) {
        return placesService. getSeats(page, pageSize);
    }

    @GetMapping(ID_PATH)
    private SeatResponse getSeat(@PathVariable("id") String id){
        return placesService.getSeat(id);
    }

    @PatchMapping(ID_PATH + "/select")
    private OrderResponse selectSeat(@PathVariable("id") String id, @RequestParam String orderId){
        return placesService.selectSeat(id, orderId);
    }

    @PatchMapping(ID_PATH + "/release")
    private OrderResponse releaseSeat(@PathVariable("id") String id){
        return placesService.releaseSeat(id);
    }
}
