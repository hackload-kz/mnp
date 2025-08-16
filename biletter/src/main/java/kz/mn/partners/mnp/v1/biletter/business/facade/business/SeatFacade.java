package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.ReleaseSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.SelectSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import kz.mn.partners.mnp.v1.biletter.business.service.CurrentUserService;
import kz.mn.partners.mnp.v1.biletter.business.service.SeatService;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatFacade {
    
    private final SeatService seatService;
    private final BookingService bookingService;
    private final CurrentUserService currentUserService;
    
    @Transactional
    public boolean selectSeat(SelectSeatRequest request) {
        SeatEntity seatEntity = seatService.getByIdWithLock(request.getSeatId());
        
        if (seatEntity.getStatus().equals(SeatStatus.FREE)) {
            seatEntity.setStatus(SeatStatus.RESERVED);
            seatService.save(seatEntity);
            
            BookingEntity bookingEntity = bookingService.getById(request.getBookingId());
            bookingEntity.getSeats().add(seatEntity);
            bookingService.save(bookingEntity);
            
            return true;
        } else {
            return false;
        }
    }
    
    @Transactional
    public boolean releaseSeat(ReleaseSeatRequest request) {
        SeatEntity seatEntity = seatService.getByIdWithLock(request.getSeatId());
        
        if (seatEntity.getStatus().equals(SeatStatus.FREE)) {
            return false;
        } else {
            UserEntity user = currentUserService.getCurrentUser();
            List<BookingEntity> bookingEntityList = bookingService.getByUser(user);
            boolean seatExistsInBooking = false;
            
            for (BookingEntity bookingEntity : bookingEntityList) {
                seatExistsInBooking = bookingEntity.getSeats().contains(seatEntity);
                
                if (seatExistsInBooking) {
                    bookingEntity.getSeats().remove(seatEntity);
                    bookingService.save(bookingEntity);
                    break;
                }
            }
            
            if (seatExistsInBooking) {
                seatEntity.setStatus(SeatStatus.FREE);
                seatService.save(seatEntity);
            }
            
            return seatExistsInBooking;
        }
    }
}
