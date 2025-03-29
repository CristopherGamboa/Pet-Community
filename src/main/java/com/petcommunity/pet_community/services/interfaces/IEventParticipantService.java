package com.petcommunity.pet_community.services.interfaces;

import java.util.List;

import com.petcommunity.pet_community.models.EventParticipant;

public interface IEventParticipantService {
    public List<EventParticipant> findByEventId(Long id);
    public List<EventParticipant> findByParticipantId(Long id);
    public Long countParticipantsByEventId(Long id);
}
