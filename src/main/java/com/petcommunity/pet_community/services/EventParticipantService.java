package com.petcommunity.pet_community.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_community.dtos.EventParticipantRequest;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.models.EventParticipant;
import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.repositories.interfaces.IEventParticipantRepository;
import com.petcommunity.pet_community.repositories.interfaces.IEventRepository;
import com.petcommunity.pet_community.repositories.interfaces.IParticipantRepository;
import com.petcommunity.pet_community.services.interfaces.IEventParticipantService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventParticipantService implements IEventParticipantService {
    private final IEventParticipantRepository eventParticipantRepository;
    private final IEventRepository eventRepository;
    private final IParticipantRepository participantRepository;

    @Autowired
    public EventParticipantService(IEventParticipantRepository eventParticipantRepository,
            IEventRepository eventRepository,
            IParticipantRepository participantRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public List<EventParticipant> findAll() {
        return eventParticipantRepository.findAll();
    }

    @Override
    public List<EventParticipant> findByEventId(Long id) {
        return eventParticipantRepository.findByEventId(id);
    }

    @Override
    public List<EventParticipant> findByParticipantId(Long id) {
        return eventParticipantRepository.findByParticipantId(id);
    }

    @Override
    public Optional<EventParticipant> save(EventParticipantRequest request) {
        Event event = eventRepository.findById(request.getEventId())
            .orElseThrow(() -> new EntityNotFoundException("Event with id " + request.getEventId() + " not found"));

        Participant participant = participantRepository.findById(request.getParticipantId())
            .orElseThrow(() -> new EntityNotFoundException("Participant with id " + request.getParticipantId() + " not found"));
        
        EventParticipant eventParticipant = EventParticipant.builder()
            .event(event)
            .participant(participant)
            .build();
        
        return Optional.of(eventParticipantRepository.save(eventParticipant));
    }

    @Override
    public Optional<EventParticipant> update(Long id, EventParticipantRequest request) {
        if (!eventParticipantRepository.existsById(id)) {
            throw new EntityNotFoundException("EventParticipant with id " + id + " not found");
        }

        Event event = eventRepository.findById(request.getEventId())
            .orElseThrow(() -> new EntityNotFoundException("Event with id " + request.getEventId() + " not found"));

        Participant participant = participantRepository.findById(request.getParticipantId())
            .orElseThrow(() -> new EntityNotFoundException("Participant with id " + request.getParticipantId() + " not found"));
        
        EventParticipant eventParticipant = EventParticipant.builder()
            .id(id)
            .event(event)
            .participant(participant)
            .build();

        return Optional.of(eventParticipantRepository.save(eventParticipant));
    }

    @Override
    public void delete(Long id) {
        if (!eventParticipantRepository.existsById(id)) {
            throw new EntityNotFoundException("EventParticipant with id " + id + " not found");
        }

        eventParticipantRepository.deleteById(id);
    }
}
