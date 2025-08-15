package mn.partners.biletter.business.mapper;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CreateBookingRequest;
import mn.partners.biletter.business.dto.response.CreateBookingResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    public CreateBookingResponse buildDto(BookingEntity entity) {
        return null;
    }

    public BookingEntity buildEntity(CreateBookingRequest request) {
        return null;
    }
}
