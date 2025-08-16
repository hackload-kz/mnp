package kz.mn.partners.mnp.v1.biletter.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.ReleaseSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.SelectSeatRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListSeatsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.facade.business.SeatFacade;
import kz.mn.partners.mnp.v1.biletter.business.facade.readonly.SeatReadOnlyFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import kz.mn.partners.mnp.v1.biletter.common.model.SeatStatus;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.API_V1_PATH;

@Tag(name = "Seats", description = "Контроллер для работы с местами")
@SecurityRequirement(name = "swagger-oauth2")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(API_V1_PATH)
@Slf4j
public class SeatController {

    private final SeatReadOnlyFacade readOnlyFacade;
    private final SeatFacade facade;

    @GetMapping("/seats")
    @ResponseStatus(HttpStatus.OK)
    public List<ListSeatsResponseItem> listSeats(
        @RequestParam(value = "page", required = false) @Min(1) Integer page,
        @RequestParam(value = "pageSize", required = false) @Min(1) @Max(20) Integer pageSize,
        @RequestParam("event_id") @NotNull Long eventId,
        @RequestParam(value = "row", required = false) @Min(1) Integer row,
        @RequestParam(value = "status", required = false) SeatStatus status
    ) {
        return readOnlyFacade.getSeats(eventId, page, pageSize, row, status);
    }

    @PatchMapping("/seats/select")
    public ResponseEntity<Void> selectSeat(@Valid @RequestBody SelectSeatRequest request) {
        boolean ok = facade.selectSeat(request);
        return ok ? ResponseEntity.ok().build()
            : ResponseEntity.status(HttpStatusCode.valueOf(419)).build();
    }

    @PatchMapping("/seats/release")
    public ResponseEntity<Void> releaseSeat(@Valid @RequestBody ReleaseSeatRequest request) {
        boolean ok = facade.releaseSeat(request);
        return ok ? ResponseEntity.ok().build()
            : ResponseEntity.status(HttpStatusCode.valueOf(419)).build();
    }
}
