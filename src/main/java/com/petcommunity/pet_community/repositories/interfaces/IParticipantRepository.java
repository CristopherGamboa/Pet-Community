package com.petcommunity.pet_community.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.models.Participant;

public interface IParticipantRepository {
    public Optional<Participant> findById(Long id);
    public Optional<Participant> findByEmail(String email);
    public List<Participant> findAll();
}
