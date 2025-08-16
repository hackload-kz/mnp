package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {


    public BookingEntity create(BookingEntity entity) {
        return null;
    }

    public void initiatePayment(InitiatePaymentRequest request) {

    }

    public void cancel(CancelBookingRequest request) {

    }

    public List<BookingEntity> getBookings() {
        return List.of();
    }
}
