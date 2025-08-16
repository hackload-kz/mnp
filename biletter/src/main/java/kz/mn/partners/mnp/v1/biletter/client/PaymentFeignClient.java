package kz.mn.partners.mnp.v1.biletter.client;


import kz.mn.partners.mnp.v1.biletter.client.request.PaymentRequest;
import kz.mn.partners.mnp.v1.biletter.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.PAYMENT_API_V1_PATH;

@FeignClient(name = "PaymentFeignClient", url = "${spring.service.payment.url}" + PAYMENT_API_V1_PATH)
public interface PaymentFeignClient {


    @PostMapping("/init")
    PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/check")
    PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/confirm")
    PaymentResponse confirmPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/cancel")
    PaymentResponse cancelPayment(@RequestBody PaymentRequest paymentRequest);
}
