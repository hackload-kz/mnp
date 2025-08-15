package mn.partners.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.response.ListSeatsResponseItem;
import mn.partners.biletter.business.service.SeatService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatReadOnlyFacade {

    private final SeatService seatService;

    public List<ListSeatsResponseItem> getSeats(Long eventId, Long page, Long pageSize) {
        return seatService.getSeats(eventId, page, pageSize);
    }
}
