package com.petcommunity.pet_community.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.services.interfaces.IParticipantService;

@RestController
@RequestMapping("api/participants")
public class ParticipantController {
    private final IParticipantService participantService;

    @Autowired
    public ParticipantController(IParticipantService participantService) {
        this.participantService = participantService;
    }

    // Obtiene todos los participantes
    // ej: localhost:8080/api/participants
    @GetMapping
    public List<Participant> findAll() {
        return participantService.findAll();
    }

    // Obtiene un participante por su id
    // ej: localhost:8080/api/participants/1
    @GetMapping("/{id}")
    public Optional<Participant> findById(@PathVariable Long id) {
        return participantService.findById(id);
    }

    // Obtiene un participante por su email
    // ej: localhost:8080/api/participants/email/cristopher@example.com
    @GetMapping("/email/{email}")
    public Optional<Participant> findByEmail(@PathVariable String email) {
        return participantService.findByEmail(email);
    }
}
