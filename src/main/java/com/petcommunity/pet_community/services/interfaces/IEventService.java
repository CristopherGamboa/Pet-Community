package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.dtos.EventRequest;
import com.petcommunity.pet_community.models.Event;

public interface IEventService {
    public List<Event> findAll();
    public List<Event> findUpcomingEvents();
    public List<Event> findByEventType(String eventType);
    public Optional<Event> findById(Long id);
    public Optional<Event> save(EventRequest request);
    public Optional<Event> update(Long id, EventRequest request);
    public void delete(Long id);
}
