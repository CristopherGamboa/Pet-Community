package com.petcommunity.pet_community.repositories.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcommunity.pet_community.models.Participant;

public interface IParticipantRepository extends JpaRepository<Participant, Long> { 
    public Optional<Participant> findByEmail(String email);
}
