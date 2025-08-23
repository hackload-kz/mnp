package kz.mnpartners.mnp.v1.paymentgatewayintegration.service;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.request.PaymentRequest;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse initPayment(PaymentRequest paymentRequest);

    PaymentResponse checkPayment(PaymentRequest paymentRequest);

    PaymentResponse confirmPayment(PaymentRequest paymentRequest);

    PaymentResponse cancelPayment(PaymentRequest paymentRequest);
}
