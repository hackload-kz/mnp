package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.ReleaseSeatRequest;
import mn.partners.biletter.business.dto.request.SelectSeatRequest;
import mn.partners.biletter.business.dto.response.ListSeatsResponseItem;
import org.springframework.stereotype.Service;
import mn.partners.biletter.common.model.SeatStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    public boolean selectSeat(SelectSeatRequest request) {
        return false;
    }

    public boolean releaseSeat(ReleaseSeatRequest request) {
        return false;
    }

    public List<ListSeatsResponseItem> getSeats(Long eventId, Long page, Long pageSize, Long row, SeatStatus status) {
        return List.of();
    }
}
