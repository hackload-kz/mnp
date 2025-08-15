package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.PaymentNotificationPayload;
import kz.mn.partners.mnp.v1.biletter.business.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;

    public void notifyPaymentCompleted(Long orderId) {
        paymentService.notifyPaymentCompleted(orderId);
    }

    public void notifyPaymentFailed(Long orderId) {
        paymentService.notifyPaymentFailed(orderId);
    }

    public void onPaymentUpdates(PaymentNotificationPayload payload) {
        paymentService.onPaymentUpdates(payload);
    }
}
