package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CancelBookingRequest;
import mn.partners.biletter.business.dto.request.InitiatePaymentRequest;
import mn.partners.biletter.business.dto.response.ListBookingsResponseItem;
import mn.partners.biletter.dal.entity.BookingEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {



    public BookingEntity create(BookingEntity entity) {
        return BookingEntity.builder()
            .id(1L)
            .eventId(entity.getEventId())
            .build();
    }

    public void initiatePayment(InitiatePaymentRequest request) {

    }

    public void cancel(CancelBookingRequest request) {

    }

    public List<ListBookingsResponseItem> getBookings() {
        return List.of();
    }
}
