package mn.partners.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.response.ListSeatsResponseItem;
import mn.partners.biletter.business.service.SeatService;
import org.springframework.stereotype.Component;
import mn.partners.biletter.common.model.SeatStatus;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatReadOnlyFacade {

    private final SeatService seatService;

    public List<ListSeatsResponseItem> getSeats(Long eventId, Long page, Long pageSize, Long row, SeatStatus status) {
        return seatService.getSeats(eventId, page, pageSize, row, status);
    }
}
