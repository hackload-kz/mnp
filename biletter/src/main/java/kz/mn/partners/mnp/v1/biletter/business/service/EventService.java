package kz.mn.partners.mnp.v1.biletter.business.service;

import lombok.RequiredArgsConstructor;
import kz.mn.partners.mnp.v1.biletter.business.dto.response.ListEventsResponseItem;
import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
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
