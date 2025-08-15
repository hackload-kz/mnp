package mn.partners.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.response.ListBookingsResponseItem;
import mn.partners.biletter.business.service.BookingService;
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
