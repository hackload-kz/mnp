package mn.partners.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CancelBookingRequest;
import mn.partners.biletter.business.dto.request.CreateBookingRequest;
import mn.partners.biletter.business.dto.request.InitiatePaymentRequest;
import mn.partners.biletter.business.dto.response.CreateBookingResponse;
import mn.partners.biletter.business.mapper.BookingMapper;
import mn.partners.biletter.business.service.BookingService;
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
