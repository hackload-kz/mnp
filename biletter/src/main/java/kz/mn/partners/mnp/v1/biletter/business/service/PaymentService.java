package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.client.PaymentFeignClient;
import kz.mn.partners.mnp.v1.biletter.client.request.PaymentRequest;
import kz.mn.partners.mnp.v1.biletter.client.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.PaymentNotificationPayload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentFeignClient paymentClient;

    public PaymentResponse initPayment(PaymentRequest paymentRequest) {
        return paymentClient.initPayment(paymentRequest);
    }

    public void notifyPaymentCompleted(Long orderId) {

    }

    public void notifyPaymentFailed(Long orderId) {

    }

    public void onPaymentUpdates(PaymentNotificationPayload payload) {

    }
}
