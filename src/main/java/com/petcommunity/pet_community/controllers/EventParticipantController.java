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

import com.petcommunity.pet_community.dtos.EventParticipantRequest;
import com.petcommunity.pet_community.models.EventParticipant;
import com.petcommunity.pet_community.services.interfaces.IEventParticipantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/event-participants")
public class EventParticipantController {
    private final IEventParticipantService eventParticipantService;

    @Autowired
    public EventParticipantController(IEventParticipantService eventParticipantService) {
        this.eventParticipantService = eventParticipantService;
    }

    // Obtiene todos los identificadores de los participantes de los eventos
    @GetMapping
    public List<EventParticipant> findAll() {
        return eventParticipantService.findAll();
    }

    // Obtiene los identificadores de los participantes de un evento
    // ej: localhost:8081/api/event-participants/event/1
    @GetMapping("/event/{id}")
    public List<EventParticipant> findByEventId(@PathVariable Long id) {
        return eventParticipantService.findByEventId(id);
    }

    // Obtiene los identificadores de los eventos de un participante
    // ej: localhost:8081/api/event-participants/participant/1
    @GetMapping("/participant/{id}")
    public List<EventParticipant> findByParticipantId(@PathVariable Long id) {
        return eventParticipantService.findByParticipantId(id);
    }

    @PostMapping
    public Optional<EventParticipant> save(@Valid @RequestBody EventParticipantRequest request) {
        return eventParticipantService.save(request);
    }

    @PutMapping("/{id}")
    public Optional<EventParticipant> update(@PathVariable Long id, @Valid @RequestBody EventParticipantRequest request) {
        return eventParticipantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventParticipantService.delete(id);
    }
}
