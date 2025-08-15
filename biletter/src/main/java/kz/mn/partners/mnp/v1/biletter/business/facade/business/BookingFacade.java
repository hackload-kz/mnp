package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.business.mapper.BookingMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFacade {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public CreateBookingResponse create(CreateBookingRequest request) {
        return bookingMapper.buildDto(bookingService.create(bookingMapper.buildEntity(request)));
    }

    public void initiatePayment(InitiatePaymentRequest request) {
        bookingService.initiatePayment(request);
    }

    public void cancel(CancelBookingRequest request) {
        bookingService.cancel(request);
    }
}
