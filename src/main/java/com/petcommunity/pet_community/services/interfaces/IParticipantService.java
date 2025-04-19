package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.dtos.ParticipantRequest;
import com.petcommunity.pet_community.models.Participant;

public interface IParticipantService {
    public Optional<Participant> findById(Long id);
    public Optional<Participant> findByEmail(String email);
    public List<Participant> findAll();
    public Optional<Participant> save(ParticipantRequest request);
    public Optional<Participant> update(Long id, ParticipantRequest request);
    public void delete(Long id);
}
