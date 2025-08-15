package mn.partners.biletter.business.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListBookingsResponseItem {
    private Long id;
    private Long eventId;
    private List<ListEventsResponseItemSeat> seats;
}