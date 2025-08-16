package kz.mn.partners.mnp.v1.biletter.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mn.partners.mnp.v1.biletter.client.dto.DataDto;
import kz.mn.partners.mnp.v1.biletter.client.dto.ReceiptDto;
import kz.mn.partners.mnp.v1.biletter.common.model.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {
    private String teamSlug;
    private String token;
    private String amount;
    private String orderId;
    private Currency currency;
    private String description;
    private String successURL;
    private String failURL;
    private String notificationURL;
    private Integer paymentExpiry;
    private String email;
    private String language;
    private String paymentId;
    private Boolean includeTransactions;
    private Boolean includeCardDetails;
    private Boolean includeCustomerInfo;
    private Boolean includeReceipt;
    private ReceiptDto receipt;
    private DataDto data;
    private String reason;
    private Boolean force;


}
