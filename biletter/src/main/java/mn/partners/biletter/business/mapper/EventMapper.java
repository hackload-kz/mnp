package mn.partners.biletter.business.mapper;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CreateEventRequest;
import mn.partners.biletter.business.dto.response.CreateEventResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public CreateEventResponse buildDto(EventEntity entity) {
        return null;
    }

    public EventEntity buildEntity(CreateEventRequest request) {
        return null;
    }
}
