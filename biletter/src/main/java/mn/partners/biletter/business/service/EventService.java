package mn.partners.biletter.business.service;

import lombok.RequiredArgsConstructor;
import mn.partners.biletter.business.dto.response.ListEventsResponseItem;
import mn.partners.biletter.dal.entity.EventEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    public EventEntity create(EventEntity event) {
        return EventEntity.builder()
            .id(1L)
            .title(event.getTitle())
            .external(event.getExternal())
            .build();
    }

    public List<ListEventsResponseItem> getEvents(String query, LocalDate date) {
        return List.of();
    }
}
