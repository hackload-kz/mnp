package kz.mn.partners.mnp.v1.biletter.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateBookingResponse;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListBookingsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.facade.business.BookingFacade;
import kz.mn.partners.mnp.v1.biletter.business.facade.readonly.BookingReadOnlyFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.API_V1_PATH;

@Tag(name = "Bookings", description = "Контроллер для работы с бронированиями")
@SecurityRequirement(name = "swagger-oauth2")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(API_V1_PATH)
@Slf4j
public class BookingController {

    private final BookingReadOnlyFacade readOnlyFacade;
    private final BookingFacade facade;

    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return facade.create(request);
    }

    @GetMapping("/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<ListBookingsResponseItem> listBookings() {
        return readOnlyFacade.getBookings();
    }

    @PatchMapping("/bookings/initiatePayment")
    @ResponseStatus(HttpStatus.OK)
    public void initiatePayment(@Valid @RequestBody InitiatePaymentRequest request) {
        facade.initiatePayment(request);
    }

    @PatchMapping("/bookings/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelBooking(@Valid @RequestBody CancelBookingRequest request) {
        facade.cancel(request);
    }
}
