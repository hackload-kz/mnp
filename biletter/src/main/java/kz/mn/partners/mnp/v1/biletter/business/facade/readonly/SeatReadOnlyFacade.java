package kz.mn.partners.mnp.v1.biletter.business.facade.readonly;

import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListSeatsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.mapper.SeatMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.SeatService;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatReadOnlyFacade {

    private final SeatService seatService;
    private final SeatMapper seatMapper;

    public List<ListSeatsResponseItem> getSeats(Long eventId, Integer page, Integer pageSize, Integer row, SeatStatus status) {
        return seatMapper.toListSeatsResponseItemList(seatService.getSeats(eventId, page, pageSize, row, status));
    }
}
