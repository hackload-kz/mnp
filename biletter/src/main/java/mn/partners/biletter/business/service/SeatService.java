package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.ReleaseSeatRequest;
import mn.partners.biletter.business.dto.request.SelectSeatRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    public boolean selectSeat(SelectSeatRequest request) {
        return false;
    }

    public boolean releaseSeat(ReleaseSeatRequest request) {
        return false;
    }
}
