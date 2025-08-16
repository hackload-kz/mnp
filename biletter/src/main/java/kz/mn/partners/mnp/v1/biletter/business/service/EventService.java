package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventEntity getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No event found with id: " + id));
    }

    public EventEntity create(EventEntity event) {
        return null;
    }

    public List<EventEntity> getEvents(String query, LocalDate date) {
        return eventRepository.getByFilter(date, query);
    }
}
