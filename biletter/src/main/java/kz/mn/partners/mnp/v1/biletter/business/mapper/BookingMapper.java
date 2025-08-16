package kz.mn.partners.mnp.v1.biletter.business.mapper;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListBookingsResponseItem;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final SeatMapper seatMapper;

    public CreateBookingResponse buildDto(BookingEntity entity) {
        return CreateBookingResponse.builder()
            .id(entity.getId())
            .build();
    }

    public BookingEntity buildEntity(CreateBookingRequest request) {
        return BookingEntity.builder()
            .build();
    }

    public List<ListBookingsResponseItem> toListBookingsResponseItemList(List<BookingEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
            .map(this::toListBookingsResponseItem)
            .collect(Collectors.toList());
    }

    private ListBookingsResponseItem toListBookingsResponseItem(BookingEntity entity) {
        if (entity == null) {
            return null;
        }
        return ListBookingsResponseItem.builder()
            .id(entity.getId())
            .eventId(entity.getEvent() != null ? entity.getEvent().getId() : null)
            .seats(entity.getSeats() != null ? entity.getSeats().stream()
                .map(seatMapper::toListEventsResponseItemSeat)
                .collect(Collectors.toList()) : null)
            .build();
    }
}
