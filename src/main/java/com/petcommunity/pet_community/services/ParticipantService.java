package com.petcommunity.pet_community.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.repositories.interfaces.IParticipantRepository;
import com.petcommunity.pet_community.services.interfaces.IParticipantService;

@Service
public class ParticipantService implements IParticipantService {

    private final IParticipantRepository petOwnerRepository;

    @Autowired
    public ParticipantService(IParticipantRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public List<Participant> findAll() {
        return petOwnerRepository.findAll();
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        return petOwnerRepository.findByEmail(email);
    }

    @Override
    public Optional<Participant> findById(Long id) {
        return petOwnerRepository.findById(id);
    }
}
