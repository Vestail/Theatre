package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.data.EventRepository;
import com.vitaliivitrenko.theatre.model.domain.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Vitalii_Vitrenko
 */
@Component
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Nullable
    public Event getByName(String name) {
        return eventRepository.find().stream().filter(e -> e.getName().equals(name))
                .limit(1).findFirst().orElse(null);
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event save(Event event) {
        if (getById(event.getId()) == null) {
            eventRepository.create(event);
        } else {
            eventRepository.update(event);
        }

        return event;
    }

    @Override
    public void remove(Event entity) {
        eventDao.delete(entity);
    }

    @Override
    @Nullable
    public Event getById(Long id) {
        return eventDao.read(id);
    }

    @Override
    public Collection<Event> getAll() {
        return eventDao.read();
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        return getAll().stream()
                .filter(e -> e.airsOnDates(from, to))
                .collect(Collectors.toSet());
    }

    public Collection<Event> getNextEvents(LocalDate to) {
        LocalDate now = LocalDate.now();
        if (to.isBefore(now)) {
            return Collections.emptyList();
        }
        return getForDateRange(now, to);
    }

}
