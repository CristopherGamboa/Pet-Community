package com.petcommunity.pet_community.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_community.models.EventParticipant;
import com.petcommunity.pet_community.services.interfaces.IEventParticipantService;

@RestController
@RequestMapping("/api/event-participants")
public class EventParticipantController {
    private final IEventParticipantService eventParticipantService;

    @Autowired
    public EventParticipantController(IEventParticipantService eventParticipantService) {
        this.eventParticipantService = eventParticipantService;
    }

    // Obtiene los identificadores de los participantes de un evento
    // ej: localhost:8080/api/event-participants/event/1
    @GetMapping("/event/{id}")
    public List<EventParticipant> findByEventId(@PathVariable Long id) {
        return eventParticipantService.findByEventId(id);
    }

    // Obtiene los identificadores de los eventos de un participante
    // ej: localhost:8080/api/event-participants/participant/1
    @GetMapping("/participant/{id}")
    public List<EventParticipant> findByParticipantId(@PathVariable Long id) {
        return eventParticipantService.findByParticipantId(id);
    }
}
