package kz.mn.partners.mnp.v1.biletter.business.service;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListBookingsResponseItem;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
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
