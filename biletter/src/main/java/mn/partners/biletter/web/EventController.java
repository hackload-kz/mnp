package mn.partners.biletter.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.partners.biletter.business.dto.request.CreateEventRequest;
import mn.partners.biletter.business.dto.response.CreateEventResponse;
import mn.partners.biletter.business.dto.response.ListEventsResponseItem;
import mn.partners.biletter.business.facade.business.EventFacade;
import mn.partners.biletter.business.facade.readonly.EventReadOnlyFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ListEventsResponseItem> listEvents() {
        return readOnlyFacade.getEvents();
    }
}
