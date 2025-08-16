package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.impl;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.ProviderFeignClient;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.service.OrdersService;
import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final ProviderFeignClient providerFeignClient;
    private final OrdersService ordersService;
    public OrderResponse createOrder() {
        return providerFeignClient.createOrder();
    }

    public OrderResponse getOrder(String id){
        return providerFeignClient.getOrder(id);
    }

    public void submitOrder(String id) {
        OrderResponse order = ordersService.getOrder(id);
        if(Objects.equals(order.getStatus(), OrderStatusEnum.STARTED)){
            providerFeignClient.submitOrder(id);
        }
    }

    public void confirmOrder(String id) {
        OrderResponse order = ordersService.getOrder(id);
        if(Objects.equals(order.getStatus(), OrderStatusEnum.SUBMITTED)) {
            providerFeignClient.confirmOrder(id);
        }
    }

    public void confirmCancel(String id) {
        OrderResponse order = ordersService.getOrder(id);
        if(Objects.equals(order.getStatus(), OrderStatusEnum.STARTED) || Objects.equals(order.getStatus(), OrderStatusEnum.SUBMITTED)) {
            providerFeignClient.confirmCancel(id);
        }
    }


}
