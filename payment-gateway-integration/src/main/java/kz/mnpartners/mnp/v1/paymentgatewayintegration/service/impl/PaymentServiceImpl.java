package kz.mnpartners.mnp.v1.paymentgatewayintegration.service.impl;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.PaymentFeignClient;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.request.PaymentRequest;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.response.PaymentResponse;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.service.PaymentService;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentFeignClient paymentClient;
    @Value("${spring.service.payment.merchant_id}")
    private String teamSlug;
    @Value("${spring.service.payment.merchant_password}")
    private String password;


    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        addTeamSlugAndToken(paymentRequest,false);
        return paymentClient.createPayment(paymentRequest);
    }

    public PaymentResponse checkPayment(PaymentRequest paymentRequest) {
        addTeamSlugAndToken(paymentRequest,true);
        return paymentClient.checkPayment(paymentRequest);
    }

    public PaymentResponse confirmPayment(PaymentRequest paymentRequest) {
        addTeamSlugAndToken(paymentRequest,false);
        return paymentClient.confirmPayment(paymentRequest);
    }

    public PaymentResponse cancelPayment(PaymentRequest paymentRequest) {
        addTeamSlugAndToken(paymentRequest,false);
        return paymentClient.cancelPayment(paymentRequest);
    }


    private void addTeamSlugAndToken(PaymentRequest paymentRequest,boolean isCheck) {
        paymentRequest.setTeamSlug(teamSlug);
        paymentRequest.setToken(isCheck ? generateCheckToken(paymentRequest.getPaymentId()) : generateToken(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getOrderId()));
    }
    private String generateToken(String amount, CurrencyEnum currency, String OrderId) {
        return Utils.sha256(amount + currency.toString() + OrderId + password + teamSlug);
    }

    private String generateCheckToken(String paymentId) {
        return Utils.sha256(paymentId + password + teamSlug);
    }
}
