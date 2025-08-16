package kz.mn.partners.mnp.v1.biletter.client.response;

import kz.mn.partners.mnp.v1.biletter.client.dto.*;
import kz.mn.partners.mnp.v1.biletter.common.model.Cancellation;
import kz.mn.partners.mnp.v1.biletter.common.model.Currency;
import kz.mn.partners.mnp.v1.biletter.common.model.PaymentStatus;
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
    private PaymentStatus status;
    private Currency currency;
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
    private Cancellation cancellationType;
    private Integer originalAmount;
    private Integer cancelledAmount;
    private LocalDateTime cancelledAt;
    private RefundDto refund;
    private DetailsDto details;


}
