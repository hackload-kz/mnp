package mn.partners.biletter.business.mapper;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CreateBookingRequest;
import mn.partners.biletter.business.dto.response.CreateBookingResponse;
import mn.partners.biletter.dal.entity.BookingEntity;
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
