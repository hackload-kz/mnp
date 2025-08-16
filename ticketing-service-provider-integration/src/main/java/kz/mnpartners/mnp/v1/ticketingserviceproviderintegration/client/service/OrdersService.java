package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.service;

import kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.client.response.OrderResponse;

public interface OrdersService {
    OrderResponse createOrder();

    OrderResponse getOrder(String id);

    void submitOrder(String id);

    void confirmOrder(String id);

    void confirmCancel(String id);

}
