package com.petcommunity.pet_community.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.models.Event;

public interface IEventRepository {
    public List<Event> findAll();
    public List<Event> findUpcomingEvents();
    public List<Event> findByDateRange(LocalDate startDate, LocalDate endDate);
    public List<Event> findByEventType(String eventType);
    public Optional<Event> findById(Long id);
}
