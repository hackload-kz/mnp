package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.PaymentNotificationPayload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public void notifyPaymentCompleted(Long orderId) {

    }

    public void notifyPaymentFailed(Long orderId) {

    }

    public void onPaymentUpdates(PaymentNotificationPayload payload) {

    }
}
