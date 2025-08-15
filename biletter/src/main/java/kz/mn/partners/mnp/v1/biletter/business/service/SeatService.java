package kz.mn.partners.mnp.v1.biletter.business.service;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.ReleaseSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.SelectSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListSeatsResponseItem;
import org.springframework.stereotype.Service;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;

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
