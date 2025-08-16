package kz.mn.partners.mnp.v1.biletter.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.PaymentNotificationPayload;
import kz.mn.partners.mnp.v1.biletter.business.facade.business.PaymentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.API_V1_PATH;

@Tag(name = "Payments", description = "Контроллер для работы с платежами")
@SecurityRequirement(name = "swagger-oauth2")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(API_V1_PATH)
@Slf4j
public class PaymentController {

    private final PaymentFacade facade;

    @GetMapping("/payments/success")
    @ResponseStatus(HttpStatus.OK)
    public void notifyPaymentCompleted(@RequestParam("orderId") @NotNull Long orderId) {
        facade.notifyPaymentCompleted(orderId);
    }

    @GetMapping("/payments/fail")
    @ResponseStatus(HttpStatus.OK)
    public void notifyPaymentFailed(@RequestParam("orderId") @NotNull Long orderId) {
        facade.notifyPaymentFailed(orderId);
    }

    @PostMapping("/payments/notifications")
    @ResponseStatus(HttpStatus.OK)
    public void onPaymentUpdates(@Valid @RequestBody PaymentNotificationPayload payload) {
        facade.onPaymentUpdates(payload);
    }
}
