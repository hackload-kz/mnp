package kz.mnpartners.mnp.v1.paymentgatewayintegration.controller;


import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.request.PaymentRequest;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.response.PaymentResponse;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kz.mnpartners.mnp.v1.paymentgatewayintegration.constant.Constants.PAYMENT_API_V1_PATH;

@RestController
@RequestMapping(value = PAYMENT_API_V1_PATH)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/init")
    private PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }
    @PostMapping("/check")
    private PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.checkPayment(paymentRequest);
    }@PostMapping("/confirm")
    private PaymentResponse confirmPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.confirmPayment(paymentRequest);
    }@PostMapping("/cancel")
    private PaymentResponse cancelPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.cancelPayment(paymentRequest);
    }
}
