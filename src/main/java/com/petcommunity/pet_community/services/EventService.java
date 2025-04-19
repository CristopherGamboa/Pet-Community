package com.petcommunity.pet_community.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_community.dtos.EventRequest;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.repositories.interfaces.IEventRepository;
import com.petcommunity.pet_community.services.interfaces.IEventService;

@Service
public class EventService implements IEventService {
    private final IEventRepository eventRepository;

    @Autowired
    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findUpcomingEvents() {
        return eventRepository.findUpcomingEvents();
    }

    @Override
    public List<Event> findByEventType(String eventType) {
        return eventRepository.findByEventType(eventType);
    }

    @Override
    public Optional<Event> save(EventRequest request) {
        Event event = Event.builder()
            .name(request.getName())
            .description(request.getDescription())
            .location(request.getLocation())
            .eventType(request.getEventType())
            .petType(request.getPetType())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public Optional<Event> update(Long id, EventRequest request) {
        if (!eventRepository.existsById(id)) {
            return Optional.empty();
        }

        Event event = Event.builder()
            .id(id)
            .name(request.getName())
            .description(request.getDescription())
            .location(request.getLocation())
            .eventType(request.getEventType())
            .petType(request.getPetType())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
