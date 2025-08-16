package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.business.mapper.BookingMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import kz.mn.partners.mnp.v1.biletter.business.service.EventService;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFacade {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final EventService eventService;

    public CreateBookingResponse create(CreateBookingRequest request) {
        EventEntity event = eventService.getEventById(request.getEventId());
        BookingEntity bookingEntity = bookingMapper.buildEntity(request);
        bookingEntity.setEvent(event);
        //todo вставить userId в bookingEntity

        return bookingMapper.buildDto(bookingService.create(bookingEntity));
    }

    public void initiatePayment(InitiatePaymentRequest request) {
        bookingService.initiatePayment(request);
    }

    public void cancel(CancelBookingRequest request) {
        bookingService.cancel(request);
    }
}
