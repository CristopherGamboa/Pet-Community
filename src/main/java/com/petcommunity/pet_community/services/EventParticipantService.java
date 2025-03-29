package com.petcommunity.pet_community.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_community.models.EventParticipant;
import com.petcommunity.pet_community.repositories.interfaces.IEventParticipantRepository;
import com.petcommunity.pet_community.services.interfaces.IEventParticipantService;

@Service
public class EventParticipantService implements IEventParticipantService {
    private final IEventParticipantRepository eventParticipantRepository;

    @Autowired
    public EventParticipantService(IEventParticipantRepository eventParticipantRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
    }

    @Override
    public Long countParticipantsByEventId(Long id) {
        return eventParticipantRepository.countParticipantsByEventId(id);
    }

    @Override
    public List<EventParticipant> findByEventId(Long id) {
        return eventParticipantRepository.findByEventId(id);
    }

    @Override
    public List<EventParticipant> findByParticipantId(Long id) {
        return eventParticipantRepository.findByParticipantId(id);
    }

}
