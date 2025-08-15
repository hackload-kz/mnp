package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CancelBookingRequest;
import mn.partners.biletter.business.dto.request.InitiatePaymentRequest;
import org.springframework.stereotype.Service;

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
}
