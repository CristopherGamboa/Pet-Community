package com.petcommunity.pet_community.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_community.dtos.EventRequest;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.services.interfaces.IEventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    // Obtiene todos los eventos registrados
    // ej: localhost:8081/api/events
    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }

    // Obtiene un evento por su id
    // ej: localhost:8081/api/events/1
    @GetMapping("/{id}")
    public Optional<Event> findById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    // Obtiene los eventos que tengan fecha posterior a la fecha actual
    // ej: localhost:8081/api/events/upcoming
    @GetMapping("/upcoming")
    public List<Event> findUpcomingEvents() {
        return eventService.findUpcomingEvents();
    }

    // Obtiene los eventos de un tipo
    // ej: localhost:8081/api/events/type/fair
    @GetMapping("/type/{type}")
    public List<Event> findByEventType(@PathVariable String type) {
        return eventService.findByEventType(type);
    }

    @PostMapping
    public Optional<Event> save(@Valid @RequestBody EventRequest request) {
        return eventService.save(request);
    }

    @PutMapping("/{id}")
    public Optional<Event> update(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        return eventService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }
}
