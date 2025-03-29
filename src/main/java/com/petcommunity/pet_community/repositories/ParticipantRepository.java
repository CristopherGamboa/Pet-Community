package com.petcommunity.pet_community.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcommunity.pet_community.models.Participant;
import com.petcommunity.pet_community.repositories.interfaces.IParticipantRepository;

@Repository
public class ParticipantRepository implements IParticipantRepository {

    private List<Participant> petOwners;

    public ParticipantRepository() {
        this.petOwners = new ArrayList<>(List.of(
            new Participant(1L, "Juan Pérez", "juan@example.com", "123456789", "Calle 123, Santiago"),
            new Participant(2L, "María López", "maria@example.com", "987654321", "Av. Central 456, Valparaíso"),
            new Participant(3L, "Cristopher Gamboa", "cristopher@example.com", "564738291", "Pasaje Los Olmos 789, Concepción"),
            new Participant(4L, "Ana González", "ana@example.com", "112233445", "Alameda 321, La Serena"),
            new Participant(5L, "Pedro Ramírez", "pedro@example.com", "998877665", "Las Palmas 567, Temuco"),
            new Participant(6L, "Sofía Herrera", "sofia@example.com", "776655443", "Cerro Alegre 876, Viña del Mar"),
            new Participant(7L, "Luis Fernández", "luis@example.com", "554433221", "Gran Avenida 432, Rancagua"),
            new Participant(8L, "Camila Torres", "camila@example.com", "667788990", "Ruta 68, Quilpué"),
            new Participant(9L, "Rodrigo Muñoz", "rodrigo@example.com", "223344556", "Los Castaños 234, Osorno"),
            new Participant(10L, "Valentina Rojas", "valentina@example.com", "332211445", "Costanera 678, Punta Arenas")
        ));
    }

    @Override
    public List<Participant> findAll() {
        return petOwners;
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        return petOwners.stream()
                .filter(owner -> owner.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Participant> findById(Long id) {
        return petOwners.stream()
                .filter(owner -> owner.getId().equals(id))
                .findFirst();
    }
}
