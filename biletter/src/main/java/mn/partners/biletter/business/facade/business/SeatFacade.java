package mn.partners.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.ReleaseSeatRequest;
import mn.partners.biletter.business.dto.request.SelectSeatRequest;
import mn.partners.biletter.business.service.SeatService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatFacade {

    private final SeatService seatService;

    public boolean selectSeat(SelectSeatRequest request) {
        return seatService.selectSeat(request);
    }

    public boolean releaseSeat(ReleaseSeatRequest request) {
        return seatService.releaseSeat(request);
    }
}
