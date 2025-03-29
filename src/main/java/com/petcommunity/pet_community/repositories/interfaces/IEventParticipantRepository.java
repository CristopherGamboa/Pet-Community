package com.petcommunity.pet_community.repositories.interfaces;

import java.util.List;

import com.petcommunity.pet_community.models.EventParticipant;

public interface IEventParticipantRepository {
    public List<EventParticipant> findByEventId(Long id);
    public List<EventParticipant> findByParticipantId(Long id);
    public Long countParticipantsByEventId(Long id);
}
