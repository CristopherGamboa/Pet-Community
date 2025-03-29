package com.petcommunity.pet_community.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.services.interfaces.IEventParticipantService;
import com.petcommunity.pet_community.services.interfaces.IEventService;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final IEventService eventService;
    private final IEventParticipantService eventParticipantService;

    @Autowired
    public EventController(IEventService eventService, IEventParticipantService eventParticipantService) {
        this.eventService = eventService;
        this.eventParticipantService = eventParticipantService;
    }

    // Obtiene todos los eventos registrados
    // ej: localhost:8080/api/events
    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }

    // Obtiene los eventos que tengan fecha posterior a la fecha actual
    // ej: localhost:8080/api/events/upcoming
    @GetMapping("/upcoming")
    public List<Event> findUpcomingEvents() {
        return eventService.findUpcomingEvents();
    }

    // Obtiene los eventos dentro del rango de fechas
    // ej: localhost:8080/api/events/date-range/2024-01-01/2024-12-31
    @GetMapping("/date-range/{startDate}/{endDate}")
    public List<Event> findByDateRange(@PathVariable LocalDate startDate, 
    @PathVariable LocalDate endDate) {
        return eventService.findByDateRange(startDate, endDate);
    }

    // Obtiene un evento por su id
    // ej: localhost:8080/api/events/1
    @GetMapping("/{id}")
    public Optional<Event> findById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    // Obtiene la cantidad de participantes de un evento
    // ej: localhost:8080/api/events/1/count-participants
    @GetMapping("/{id}/count-participants")
    public Map<String, Long> countParticipantsByEventId(@PathVariable Long id) {
        return eventParticipantService.countParticipantsByEventId(id);
    }

    // Obtiene los eventos de un tipo
    // ej: localhost:8080/api/events/type/fair
    @GetMapping("/type/{type}")
    public List<Event> findByEventType(@PathVariable String type) {
        return eventService.findByEventType(type);
    }
}
