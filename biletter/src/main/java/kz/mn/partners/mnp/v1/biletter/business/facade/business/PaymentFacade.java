package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.PaymentNotificationPayload;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import kz.mn.partners.mnp.v1.biletter.business.service.PaymentService;
import kz.mn.partners.mnp.v1.biletter.business.service.SeatService;
import kz.mn.partners.mnp.v1.biletter.common.model.BookingStatus;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final SeatService seatService;

    @Transactional
    public void notifyPaymentCompleted(String orderId) {
        try {
            BookingEntity bookingEntity = bookingService.getByOrderIdWithLock(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No booking found with orderId: " + orderId));
            
            if (bookingEntity.getStatus() == BookingStatus.CONFIRMED) {
                return;
            }
            
            if (bookingEntity.getStatus() != BookingStatus.PAYMENT_INITIATED) {
                log.error("Cannot confirm payment for orderId {} with status: {}", 
                         orderId, bookingEntity.getStatus());
                throw new IllegalStateException("Invalid booking status for confirmation: " + bookingEntity.getStatus());
            }
            
            bookingEntity.setStatus(BookingStatus.CONFIRMED);
            bookingService.save(bookingEntity);
            
            bookingEntity.getSeats().forEach(seat -> {
                if (seat.getStatus() != SeatStatus.SOLD) {
                    seat.setStatus(SeatStatus.SOLD);
                    seatService.save(seat);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Payment completion processing failed", e);
        }
    }

    @Transactional
    public void notifyPaymentFailed(String orderId) {
        try {
            BookingEntity bookingEntity = bookingService.getByOrderIdWithLock(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No booking found with orderId: " + orderId));
            
            if (bookingEntity.getStatus() == BookingStatus.CANCELLED) {
                return;
            }
            
            if (bookingEntity.getStatus() != BookingStatus.PAYMENT_INITIATED) {
                throw new IllegalStateException("Invalid booking status for cancellation: " + bookingEntity.getStatus());
            }
            
            bookingEntity.setStatus(BookingStatus.CANCELLED);
            bookingService.save(bookingEntity);
            
            bookingEntity.getSeats().forEach(seat -> {
                if (seat.getStatus() != SeatStatus.FREE) {
                    seat.setStatus(SeatStatus.FREE);
                    seatService.save(seat);
                }
            });
            

        } catch (Exception e) {
            throw new RuntimeException("Payment failure processing failed", e);
        }
    }

    public void onPaymentUpdates(PaymentNotificationPayload payload) {
        paymentService.onPaymentUpdates(payload);
    }
}
