package kz.mn.partners.mnp.v1.biletter.business.mapper;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CreateEventRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.CreateEventResponse;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListEventsResponseItem;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ListEventsResponseItem> toListEventsResponseItemList(List<EventEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
            .map(this::toListEventsResponseItem)
            .collect(Collectors.toList());
    }

    private ListEventsResponseItem toListEventsResponseItem(EventEntity entity) {
        if (entity == null) {
            return null;
        }
        return ListEventsResponseItem.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .build();
    }
}
