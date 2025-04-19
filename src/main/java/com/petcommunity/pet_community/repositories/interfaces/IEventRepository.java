package com.petcommunity.pet_community.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petcommunity.pet_community.models.Event;

public interface IEventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventType(String eventType);

    @Query("SELECT e FROM Event e WHERE e.startDate > CURRENT_TIMESTAMP")
    List<Event> findUpcomingEvents();
}
