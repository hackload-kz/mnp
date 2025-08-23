package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.business.mapper.BookingMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import kz.mn.partners.mnp.v1.biletter.business.service.EventService;
import kz.mn.partners.mnp.v1.biletter.client.ProviderFeignClient;
import kz.mn.partners.mnp.v1.biletter.client.response.OrderResponse;
import kz.mn.partners.mnp.v1.biletter.common.model.BookingStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import kz.mn.partners.mnp.v1.biletter.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFacade {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final EventService eventService;
    private final ProviderFeignClient providerClient;
    private final SecurityUtils securityUtils;

    public CreateBookingResponse create(CreateBookingRequest request) {
        EventEntity event = eventService.getEventById(request.getEventId());
        BookingEntity bookingEntity = BookingEntity
            .builder()
            .event(event)
            .status(BookingStatus.CREATED)
            .user(securityUtils.getCurrentUserId())
            .build();
        if (event.getExternal()) {
            OrderResponse order = providerClient.createOrder();
            bookingEntity.setOrderId(order.getOrderId());
        }
        return bookingMapper.buildDto(bookingService.save(bookingEntity));
    }

    public void initiatePayment(InitiatePaymentRequest request) {
        bookingService.initiatePayment(request);
    }



    public void cancel(CancelBookingRequest request) {
        bookingService.cancel(request);
    }
}
