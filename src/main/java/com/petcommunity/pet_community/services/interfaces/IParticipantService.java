package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.models.Participant;

public interface IParticipantService {
    public Optional<Participant> findById(Long id);
    public Optional<Participant> findByEmail(String email);
    public List<Participant> findAll();
}
