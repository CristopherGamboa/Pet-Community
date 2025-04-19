package com.petcommunity.pet_community.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_community.dtos.PetRequest;
import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.models.Pet;
import com.petcommunity.pet_community.repositories.interfaces.IParticipantRepository;
import com.petcommunity.pet_community.repositories.interfaces.IPetRepository;
import com.petcommunity.pet_community.services.interfaces.IPetService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService implements IPetService {
    
    private final IPetRepository petRepository;
    private final IParticipantRepository participantRepository;

    @Autowired
    public PetService(IPetRepository petRepository, IParticipantRepository participantRepository) {
        this.petRepository = petRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findByParticipantId(Long id) {
        return petRepository.findByParticipantId(id);
    }

    @Override
    public List<Pet> findByType(String type) {
        return petRepository.findByType(type);
    }

    @Override
    public Optional<Pet> save(PetRequest request) {
        Participant participant = participantRepository.findById(request.getParticipantId())
            .orElseThrow(() -> new EntityNotFoundException("Participant with id " + request.getParticipantId() + " not found"));

        Pet pet = Pet.builder()
            .name(request.getName())
            .type(request.getType())
            .participant(participant)
            .build();

        return Optional.of(petRepository.save(pet));
    }

    @Override
    public Optional<Pet> update(Long id, PetRequest request) {
        if (!petRepository.existsById(id)) {
            return Optional.empty();
        }

        Participant participant = participantRepository.findById(request.getParticipantId())
            .orElseThrow(() -> new EntityNotFoundException("Participant with id " + request.getParticipantId() + " not found"));

        Pet pet = Pet.builder()
            .id(id)
            .name(request.getName())
            .type(request.getType())
            .participant(participant)
            .build();

        return Optional.of(petRepository.save(pet));
    }

    @Override
    public void delete(Long id) {
        petRepository.deleteById(id);
    }
}
