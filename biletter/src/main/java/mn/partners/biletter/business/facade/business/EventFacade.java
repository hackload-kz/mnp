package mn.partners.biletter.business.facade.business;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CreateEventRequest;
import mn.partners.biletter.business.dto.response.CreateEventResponse;
import mn.partners.biletter.business.mapper.EventMapper;
import mn.partners.biletter.business.service.EventService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventFacade {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public CreateEventResponse create(CreateEventRequest request) {
        return eventMapper.buildDto(eventService.create(eventMapper.buildEntity(request)));
    }
}
