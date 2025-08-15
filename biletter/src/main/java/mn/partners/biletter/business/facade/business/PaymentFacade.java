package mn.partners.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.service.PaymentService;
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
}
