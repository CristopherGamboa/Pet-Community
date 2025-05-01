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

import com.petcommunity.pet_community.dtos.EventRequest;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.services.interfaces.IEventService;

import jakarta.persistence.EntityNotFoundException;
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
    public CollectionModel<EntityModel<Event>> findAll() {
        List<Event> events = eventService.findAll(); 

        List<EntityModel<Event>> eventModels = events.stream()
            .map(event -> EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(event.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<Event>> eventCollectionModel = CollectionModel
            .of(eventModels, linkBuilder.withRel("events"));

        return eventCollectionModel;
    }

    // Obtiene un evento por su id
    // ej: localhost:8081/api/events/1
    @GetMapping("/{id}")
    public EntityModel<Event> findById(@PathVariable Long id) {
        Optional<Event> event = eventService.findById(id);

        if (!event.isPresent()) {
            throw new EntityNotFoundException("Event not found with id: " + id);
        }

        return EntityModel.of(event.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("events"));
    }

    // Obtiene los eventos que tengan fecha posterior a la fecha actual
    // ej: localhost:8081/api/events/upcoming
    @GetMapping("/upcoming")
    public CollectionModel<EntityModel<Event>> findUpcomingEvents() {
        List<Event> events = eventService.findUpcomingEvents(); 

        List<EntityModel<Event>> eventModels = events.stream()
            .map(event -> EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(event.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<Event>> eventCollectionModel = CollectionModel
            .of(eventModels, linkBuilder.withRel("events"));

        return eventCollectionModel;
    }

    // Obtiene los eventos de un tipo
    // ej: localhost:8081/api/events/type/fair
    @GetMapping("/type/{type}")
    public CollectionModel<EntityModel<Event>> findByEventType(@PathVariable String type) {
        List<Event> events = eventService.findByEventType(type);

        List<EntityModel<Event>> eventModels = events.stream()
            .map(event -> EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(event.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<Event>> eventCollectionModel = CollectionModel
            .of(eventModels, linkBuilder.withRel("events"));

        return eventCollectionModel;
    }

    @PostMapping
    public EntityModel<Event> save(@Valid @RequestBody EventRequest request) {
        Optional<Event> savedEvent = eventService.save(request);

        return EntityModel.of(savedEvent.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedEvent.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("events"));
    }

    @PutMapping("/{id}")
    public EntityModel<Event> update(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        Optional<Event> savedEvent = eventService.update(id, request);

        return EntityModel.of(savedEvent.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedEvent.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("events"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }
}
