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

import com.petcommunity.pet_community.dtos.ParticipantRequest;
import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.services.interfaces.IParticipantService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/participants")
public class ParticipantController {
    private final IParticipantService participantService;

    @Autowired
    public ParticipantController(IParticipantService participantService) {
        this.participantService = participantService;
    }

    // Obtiene todos los participantes
    // ej: localhost:8081/api/participants
    @GetMapping
    public CollectionModel<EntityModel<Participant>> findAll() {
        List<Participant> participants = participantService.findAll(); 

        List<EntityModel<Participant>> participantModels = participants.stream()
            .map(participant -> EntityModel.of(participant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .findById(participant.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        CollectionModel<EntityModel<Participant>> participantCollectionModel = CollectionModel
            .of(participantModels, linkBuilder.withRel("participants"));

        return participantCollectionModel;
    }

    // Obtiene un participante por su id
    // ej: localhost:8081/api/participants/1
    @GetMapping("/{id}")
    public EntityModel<Participant> findById(@PathVariable Long id) {
        Optional<Participant> participant = participantService.findById(id);

        if (!participant.isPresent()) {
            throw new EntityNotFoundException("Participant not found with id: " + id);
        }

        return EntityModel.of(participant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("participants"));
    }

    // Obtiene un participante por su email
    // ej: localhost:8081/api/participants/email/cristopher@example.com
    @GetMapping("/email/{email}")
    public EntityModel<Participant> findByEmail(@PathVariable String email) {
        Optional<Participant> participant = participantService.findByEmail(email);

        if (!participant.isPresent()) {
            throw new EntityNotFoundException("Participant not found with email: " + email);
        }

        return EntityModel.of(participant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(participant.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("participants"));
    }

    @PostMapping
    public EntityModel<Participant> save(@Valid @RequestBody ParticipantRequest request) {
        Optional<Participant> savedParticipant = participantService.save(request);

        return EntityModel.of(savedParticipant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedParticipant.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("participants"));
    }

    @PutMapping("/{id}")
    public EntityModel<Participant> update(@PathVariable Long id, @Valid @RequestBody ParticipantRequest request) {
        Optional<Participant> savedParticipant = participantService.update(id, request);

        return EntityModel.of(savedParticipant.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(savedParticipant.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll()).withRel("participants"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        participantService.delete(id);
    }
}
