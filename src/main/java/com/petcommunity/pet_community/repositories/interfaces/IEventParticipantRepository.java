package com.petcommunity.pet_community.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcommunity.pet_community.models.EventParticipant;

public interface IEventParticipantRepository extends JpaRepository<EventParticipant, Long> {
    List<EventParticipant> findByEventId(Long id);
    List<EventParticipant> findByParticipantId(Long id);
}
