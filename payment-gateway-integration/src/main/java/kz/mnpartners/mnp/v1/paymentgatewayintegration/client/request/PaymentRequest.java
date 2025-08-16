package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto.DataDto;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto.ReceiptDto;
import kz.mnpartners.mnp.v1.paymentgatewayintegration.enums.CurrencyEnum;
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
    private CurrencyEnum currency;
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
