package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.dtos.EventParticipantRequest;
import com.petcommunity.pet_community.models.EventParticipant;

public interface IEventParticipantService {
    public List<EventParticipant> findAll();
    public List<EventParticipant> findByEventId(Long id);
    public List<EventParticipant> findByParticipantId(Long id);
    public Optional<EventParticipant> save(EventParticipantRequest request);
    public Optional<EventParticipant> update(Long id, EventParticipantRequest request);
    public void delete(Long id);
}
