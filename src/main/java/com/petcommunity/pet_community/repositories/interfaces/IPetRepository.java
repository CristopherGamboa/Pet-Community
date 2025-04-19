package com.petcommunity.pet_community.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcommunity.pet_community.models.Pet;

public interface IPetRepository extends JpaRepository<Pet, Long> {
    public List<Pet> findByParticipantId(Long id);
    public List<Pet> findByType(String type);
}
