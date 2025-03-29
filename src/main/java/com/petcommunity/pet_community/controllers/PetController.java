package com.petcommunity.pet_community.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_community.models.Pet;
import com.petcommunity.pet_community.services.interfaces.IPetService;

@RestController
@RequestMapping("api/pets")
public class PetController {
    private final IPetService petService;

    @Autowired
    public PetController(IPetService petService) {
        this.petService = petService;
    }

    // Obtiene todas las mascotas
    // ej: localhost:8080/api/pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.findAll();
    }

    // Obtiene una mascota por su id
    // ej: localhost:8080/api/pets/1
    @GetMapping("/{id}")
    public Optional<Pet> getPetById(@PathVariable Long id) {
        return petService.findById(id);
    }

    // Obtiene las mascotas de un participante
    // ej: localhost:8080/api/pets/participant/3
    @GetMapping("/participant/{id}")
    public List<Pet> getPetsByParticipantId(@PathVariable Long id) {
        return petService.findByParticipantId(id);
    }

    // Obtiene las mascotas de un tipo
    // ej: localhost:8080/api/pets/type/dog
    @GetMapping("/type/{type}")
    public List<Pet> getPetsByType(@PathVariable String type) {
        return petService.findByType(type);
    }
}
