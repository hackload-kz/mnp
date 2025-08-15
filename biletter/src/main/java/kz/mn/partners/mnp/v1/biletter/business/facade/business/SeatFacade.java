package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.SelectSeatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatFacade {

    private final kz.mn.partners.mnp.v1.biletter.business.service.SeatService seatService;

    public boolean selectSeat(SelectSeatRequest request) {
        return seatService.selectSeat(request);
    }

    public boolean releaseSeat(kz.mn.partners.mnp.v1.biletter.business.dto.request.ReleaseSeatRequest request) {
        return seatService.releaseSeat(request);
    }
}
