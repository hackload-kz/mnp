package kz.mn.partners.mnp.v1.biletter.business.mapper;

import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListEventsResponseItemSeat;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListSeatsResponseItem;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SeatMapper {

    public List<ListSeatsResponseItem> toListSeatsResponseItemList(List<SeatEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
            .map(this::toListSeatsResponseItem)
            .collect(Collectors.toList());
    }

    public ListEventsResponseItemSeat toListEventsResponseItemSeat(SeatEntity seat) {
        return ListEventsResponseItemSeat.builder()
            .id(seat.getId())
            .build();
    }

    private ListSeatsResponseItem toListSeatsResponseItem(SeatEntity entity) {
        return ListSeatsResponseItem.builder()
            .id(entity.getId())
            .row(entity.getSeatRow() != null ? entity.getSeatRow().longValue() : null)
            .number(entity.getNumber() != null ? entity.getNumber().longValue() : null)
            .status(entity.getStatus())
            .price(entity.getPrice())
            .build();
    }
}
