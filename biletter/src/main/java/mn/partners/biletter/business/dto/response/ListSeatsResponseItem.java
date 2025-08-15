package mn.partners.biletter.business.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListSeatsResponseItem {
    private Long id;
    private Long row;
    private Long number;
    private Boolean reserved;
}