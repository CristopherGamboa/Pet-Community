package com.petcommunity.pet_community.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import jakarta.persistence.EntityNotFoundException;
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
    public CollectionModel<EntityModel<EventParticipant>> findAll() {
        List<EventParticipant> eventParticipants = eventParticipantService.findAll(); 

        List<EntityModel<EventParticipant>> eventParticipantModels = eventParticipants.stream()
            .map(eventParticipant -> EntityModel.of(eventParticipant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(eventParticipant.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<EventParticipant>> eventParticipantCollectionModel = CollectionModel
            .of(eventParticipantModels, linkBuilder.withRel("events"));

        return eventParticipantCollectionModel;
    }

    @GetMapping("/{id}")
    public EntityModel<EventParticipant> findById(@PathVariable Long id) {
        Optional<EventParticipant> eventParticipant = eventParticipantService.findById(id);

        if (!eventParticipant.isPresent()) {
            throw new EntityNotFoundException("Event not found with id: " + id);
        }

        return EntityModel.of(eventParticipant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("event-participants"));
    }

    // Obtiene los identificadores de los participantes de un evento
    // ej: localhost:8081/api/event-participants/event/1
    @GetMapping("/event/{id}")
    public CollectionModel<EntityModel<EventParticipant>> findByEventId(@PathVariable Long id) {
        List<EventParticipant> eventParticipants = eventParticipantService.findByEventId(id); 

        List<EntityModel<EventParticipant>> eventParticipantModels = eventParticipants.stream()
            .map(eventParticipant -> EntityModel.of(eventParticipant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(eventParticipant.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<EventParticipant>> eventParticipantCollectionModel = CollectionModel
            .of(eventParticipantModels, linkBuilder.withRel("event-participants"));

        return eventParticipantCollectionModel;
    }

    // Obtiene los identificadores de los eventos de un participante
    // ej: localhost:8081/api/event-participants/participant/1
    @GetMapping("/participant/{id}")
    public CollectionModel<EntityModel<EventParticipant>> findByParticipantId(@PathVariable Long id) {
        List<EventParticipant> events = eventParticipantService.findByParticipantId(id); 

        List<EntityModel<EventParticipant>> eventModels = events.stream()
            .map(event -> EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(event.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<EventParticipant>> eventCollectionModel = CollectionModel
            .of(eventModels, linkBuilder.withRel("event-participants"));

        return eventCollectionModel;
    }

    @PostMapping
    public EntityModel<EventParticipant> save(@Valid @RequestBody EventParticipantRequest request) {
        Optional<EventParticipant> savedEventParticipant = eventParticipantService.save(request);

        return EntityModel.of(savedEventParticipant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedEventParticipant.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("event-participants"));
    }

    @PutMapping("/{id}")
    public EntityModel<EventParticipant> update(@PathVariable Long id, @Valid @RequestBody EventParticipantRequest request) {
        Optional<EventParticipant> savedEventParticipant = eventParticipantService.save(request);

        return EntityModel.of(savedEventParticipant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedEventParticipant.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("event-participants"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventParticipantService.delete(id);
    }
}
