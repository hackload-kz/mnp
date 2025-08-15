package kz.mn.partners.mnp.v1.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListEventsResponseItem;
import kz.mn.partners.mnp.v1.biletter.business.service.EventService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class EventReadOnlyFacade {

    private final EventService eventService;

    public List<ListEventsResponseItem> getEvents(String query, LocalDate date) {
        return eventService.getEvents(query, date);
    }
}
