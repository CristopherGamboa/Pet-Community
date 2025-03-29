package com.petcommunity.pet_community.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Event> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByDateRange(startDate, endDate);
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
}
