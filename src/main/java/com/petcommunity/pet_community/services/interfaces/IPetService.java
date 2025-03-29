package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.models.Pet;

public interface IPetService {
    public Optional<Pet> findById(Long id);
    public List<Pet> findAll();
    public List<Pet> findByParticipantId(Long id);
    public List<Pet> findByType(String type);
}
