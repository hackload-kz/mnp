package kz.mn.partners.mnp.v1.biletter.business.facade.business;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateEventRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateEventResponse;
import kz.mn.partners.mnp.v1.biletter.business.mapper.EventMapper;
import kz.mn.partners.mnp.v1.biletter.business.service.EventService;
import lombok.RequiredArgsConstructor;
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
