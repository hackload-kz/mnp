package kz.mn.partners.mnp.v1.biletter.business.facade.readonly;

import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListSeatsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.mapper.ProviderMapper;
import kz.mn.partners.mnp.v1.biletter.business.mapper.SeatMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.EventService;
import kz.mn.partners.mnp.v1.biletter.business.service.SeatService;
import kz.mn.partners.mnp.v1.biletter.client.ProviderFeignClient;
import kz.mn.partners.mnp.v1.biletter.client.response.SeatResponse;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.SeatRepository;
import kz.mn.partners.mnp.v1.biletter.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SeatReadOnlyFacade {

    private final SeatService seatService;
    private final SeatMapper seatMapper;
    private final EventService eventService;
    private final ProviderMapper providerMapper;
    private final ProviderFeignClient providerClient;
    private final SeatRepository seatRepository;

//    @Transactional транзакции падают
    public List<ListSeatsResponseItem> getSeats(Long eventId, Integer page, Integer pageSize, Integer row, SeatStatus status) {
        EventEntity event = eventService.getEventById(eventId);
        if (event.getExternal() && Objects.equals(status, SeatStatus.FREE)) {
            List<SeatResponse> seats = providerClient.getSeats(page, pageSize);


            List<SeatEntity> result = new ArrayList<>();
            for (SeatResponse seat : seats) {
                SeatEntity existing = seatRepository.findByExternalSeatId(seat.getId())
                    .orElse(null);
                if (!ObjectUtils.isEmpty(existing)) {
                    existing.setStatus(SeatStatus.FREE);
                    result.add(existing);
                } else {
                    SeatEntity seatEntity = providerMapper.toListSeatEntity(seat);
                    seatEntity.setEvent(event);
                    seatEntity.setStatus(SeatStatus.FREE);
                    seatEntity.setPrice(Utils.calculatePrice(seat.getRow(), seat.getSeat()));
                    seatEntity.setExternalSeatId(seat.getId());
                    result.add(seatEntity);
                }
            }
            return seatMapper.toListSeatsResponseItemList(seatRepository.saveAll(result));
        }
        return seatMapper.toListSeatsResponseItemList(seatService.getSeats(eventId, page, pageSize, row, status));
    }
}
