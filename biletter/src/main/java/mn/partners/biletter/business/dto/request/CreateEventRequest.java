package mn.partners.biletter.business.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateEventRequest {
    private String title;

    @Builder.Default
    private Boolean external = false;
}