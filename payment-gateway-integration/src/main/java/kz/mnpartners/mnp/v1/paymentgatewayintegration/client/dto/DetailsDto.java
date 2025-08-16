package kz.mnpartners.mnp.v1.paymentgatewayintegration.client.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class DetailsDto {
    private String reason;
    private Boolean wasForced;
    private Time processingDuration;
}
