package kz.mnpartners.mnp.v1.paymentgatewayintegration.client;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.request.PaymentRequest;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProviderFeignClient", url = "${app.base-url.event-provider}/api/v1/")
public interface PaymentFeignClient {

    @PostMapping("PaymentInit/init")
    PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("PaymentCheck/check")
    PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("PaymentConfirm/confirm")
    PaymentResponse confirmPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("PaymentConfirm/cancel")
    PaymentResponse cancelPayment(@RequestBody PaymentRequest paymentRequest);
}
