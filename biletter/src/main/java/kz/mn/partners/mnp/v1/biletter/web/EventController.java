package kz.mn.partners.mnp.v1.biletter.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateEventRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateEventResponse;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListEventsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.facade.business.EventFacade;
import kz.mn.partners.mnp.v1.biletter.business.facade.readonly.EventReadOnlyFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Tag(name = "Events", description = "Контроллер для работы с событиями")
@SecurityRequirement(name = "swagger-oauth2")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
@Slf4j
public class EventController {

    private final EventReadOnlyFacade readOnlyFacade;
    private final EventFacade facade;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEventResponse createEvent(@Valid @RequestBody CreateEventRequest request) {
        return facade.create(request);
    }

    @GetMapping("/events")
    @ResponseStatus(HttpStatus.OK)
    public List<ListEventsResponseItem> listEvents(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "date", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return readOnlyFacade.getEvents(query, date);
    }
}
