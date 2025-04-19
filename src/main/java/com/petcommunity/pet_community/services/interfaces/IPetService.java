package com.petcommunity.pet_community.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcommunity.pet_community.dtos.PetRequest;
import com.petcommunity.pet_community.models.Pet;

public interface IPetService {
    public Optional<Pet> findById(Long id);
    public List<Pet> findAll();
    public List<Pet> findByParticipantId(Long id);
    public List<Pet> findByType(String type);
    public Optional<Pet> save(PetRequest request);
    public Optional<Pet> update(Long id, PetRequest request);
    public void delete(Long id);
}
