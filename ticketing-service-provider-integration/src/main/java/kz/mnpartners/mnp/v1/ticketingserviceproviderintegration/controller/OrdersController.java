package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.controller;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.common.constant.Constants.*;

@RestController
@RequestMapping(value = ORDERS_API_V1_PATH)
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    private OrderResponse createOrder() {
        return ordersService.createOrder();
    }
    @GetMapping(GET_PATH)
    private OrderResponse getOrder(@PathVariable("id") String id) {
        return ordersService.getOrder(id);
    }

    @PatchMapping(ID_PATH + "/submit")
    private void submitOrder(@PathVariable("id") String id){
        ordersService.submitOrder(id);
    }

    @PatchMapping(ID_PATH + "/confirm")
    private void confirmOrder(@PathVariable("id") String id){
        ordersService.confirmOrder(id);
    }

    @PatchMapping(ID_PATH + "/cancel")
    private void confirmCancel(@PathVariable("id") String id){
        ordersService.confirmCancel(id);
    }

}
