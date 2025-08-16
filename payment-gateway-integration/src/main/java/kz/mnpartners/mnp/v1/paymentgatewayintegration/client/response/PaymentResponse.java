package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.response;

import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto.*;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CancellationEnum;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Jacksonized
public class PaymentResponse {
    private Boolean success;
    private String paymentId;
    private String orderId;
    private PaymentStatusEnum status;
    private CurrencyEnum currency;
    private String paymentURL;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private List<PaymentDto> payments;
    private Integer totalCount;
    private Integer authorizedAmount;
    private Integer confirmedAmount;
    private Integer remainingAmount;
    private LocalDateTime confirmedAt;
    private BankDetailsDto bankDetails;
    private FeesDto fees;
    private SettlementDto settlement;
    private CancellationEnum cancellationType;
    private Integer originalAmount;
    private Integer cancelledAmount;
    private LocalDateTime cancelledAt;
    private RefundDto refund;
    private DetailsDto details;


}
