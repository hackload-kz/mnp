package mn.partners.biletter.business.mapper;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.request.CreateEventRequest;
import mn.partners.biletter.business.dto.response.CreateEventResponse;
import mn.partners.biletter.dal.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public CreateEventResponse buildDto(EventEntity entity) {
        return CreateEventResponse.builder()
            .id(entity.getId())
            .build();
    }

    public EventEntity buildEntity(CreateEventRequest request) {
        return EventEntity.builder()
            .title(request.getTitle())
            .external(Boolean.TRUE.equals(request.getExternal()))
            .build();
    }
}
