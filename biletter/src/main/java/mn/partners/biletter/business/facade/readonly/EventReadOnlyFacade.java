package mn.partners.biletter.business.facade.readonly;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.response.ListEventsResponseItem;
import mn.partners.biletter.business.service.EventService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventReadOnlyFacade {

    private final EventService eventService;

    public List<ListEventsResponseItem> getEvents() {
        return eventService.getEvents();
    }
}
