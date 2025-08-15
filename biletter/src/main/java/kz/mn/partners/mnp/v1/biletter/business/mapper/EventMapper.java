package kz.mn.partners.mnp.v1.biletter.business.mapper;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateEventRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateEventResponse;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import lombok.RequiredArgsConstructor;
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
