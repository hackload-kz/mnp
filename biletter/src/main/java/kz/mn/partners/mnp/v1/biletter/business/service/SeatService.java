package kz.mn.partners.mnp.v1.biletter.business.service;

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
    
    public SeatEntity getById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No seat found with id: " + id));
    }
    
    public SeatEntity getByIdWithLock(Long id) {
        return seatRepository.findByIdWithLock(id)
            .orElseThrow(() -> new IllegalArgumentException("No seat found with id: " + id));
    }
    
    public List<SeatEntity> getSeats(Long eventId, Integer page, Integer pageSize, Integer row, SeatStatus status) {
        return seatRepository.findSeatsByCriteria(eventId, row, status, PageRequest.of(page, pageSize, Sort.by("seatRow").ascending().and(Sort.by("number")))).getContent();
    }
    
    public SeatEntity save(SeatEntity seat) {
        return seatRepository.save(seat);
    }
}
