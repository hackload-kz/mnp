package kz.mn.partners.mnp.v1.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListBookingsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingReadOnlyFacade {

    private final BookingService bookingService;

    public List<ListBookingsResponseItem> getBookings() {
        return bookingService.getBookings();
    }
}
