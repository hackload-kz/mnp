package kz.mn.partners.mnp.v1.biletter.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailsDto {
    private String reason;
    private Boolean wasForced;
    private Time processingDuration;
}
