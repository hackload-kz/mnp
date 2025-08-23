package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.dal.entity.EventEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventEntity getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No event found with id: " + id));
    }

    @CacheEvict(value = "events", allEntries = true)
    public EventEntity create(EventEntity event) {
        return eventRepository.save(event);
    }

    public List<EventEntity> getEvents(String query, LocalDate date, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        String searchQuery = parseQuery(query);
        if (date != null) {
            LocalDateTime dateFrom = date.atStartOfDay();
            LocalDateTime dateTo = date.plusDays(1).atStartOfDay();
            if (query != null) {
                return eventRepository.findByDateAndSearchQuery(dateFrom, dateTo, searchQuery, pageable).getContent();
            } else {
                return eventRepository.findByDate(dateFrom, dateTo, pageable).getContent();
            }
        } else if (query != null) {
            return eventRepository.findBySearchQuery(searchQuery, pageable).getContent();
        }
        return eventRepository.findAll(pageable).getContent();
    }

    private String parseQuery(String query) {
        if (query == null) return null;
        return String.join("&", query.trim().split("\\s+"));
    }
}
