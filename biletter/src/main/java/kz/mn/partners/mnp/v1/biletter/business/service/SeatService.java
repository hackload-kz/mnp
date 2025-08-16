package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.ReleaseSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.SelectSeatRequest;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public boolean selectSeat(SelectSeatRequest request) {
        return false;
    }

    public boolean releaseSeat(ReleaseSeatRequest request) {
        return false;
    }

    public List<SeatEntity> getSeats(Long eventId, Integer page, Integer pageSize, Integer row, SeatStatus status) {
        return seatRepository.findSeatsByCriteria(eventId, row, status, PageRequest.of(page, pageSize, Sort.by("seatRow").ascending().and(Sort.by("number")))).getContent();
    }
}
