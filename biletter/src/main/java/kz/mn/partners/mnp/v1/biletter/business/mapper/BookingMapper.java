package kz.mn.partners.mnp.v1.biletter.business.mapper;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    public CreateBookingResponse buildDto(BookingEntity entity) {
        return CreateBookingResponse.builder()
            .id(entity.getId())
            .build();
    }

    public BookingEntity buildEntity(CreateBookingRequest request) {
        return BookingEntity.builder()
            .eventId(request.getEventId())
            .build();
    }
}
