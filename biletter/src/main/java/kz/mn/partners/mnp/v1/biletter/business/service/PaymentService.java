package kz.mn.partners.mnp.v1.biletter.business.service;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.PaymentNotificationPayload;
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
