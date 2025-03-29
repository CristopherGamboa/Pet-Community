package com.petcommunity.pet_community.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.repositories.interfaces.IEventRepository;

@Repository
public class EventRepository implements IEventRepository {
    private final List<Event> events;

    public EventRepository(List<Event> events) {
        this.events = new ArrayList<>(List.of(
                new Event(1L, "Dog Fair", "A fair for all dog lovers", "New York", EventType.FAIR, List.of(PetType.DOG), LocalDateTime.of(2024, 5, 1, 10, 0), LocalDateTime.of(2024, 5, 1, 18, 0)),
                new Event(2L, "Cat Competition", "Competitive event for cats", "Los Angeles", EventType.COMPETITION, List.of(PetType.CAT), LocalDateTime.of(2024, 6, 15, 9, 0), LocalDateTime.of(2024, 6, 15, 17, 0)),
                new Event(3L, "Pet Workshop", "Learn how to train your pets", "Chicago", EventType.WORKSHOP, List.of(PetType.DOG, PetType.CAT), LocalDateTime.of(2024, 7, 10, 14, 0), LocalDateTime.of(2024, 7, 10, 16, 0)),
                new Event(4L, "Dog and Cat Fair", "A fair for dogs and cats", "San Francisco", EventType.FAIR, List.of(PetType.DOG, PetType.CAT), LocalDateTime.of(2025, 8, 20, 11, 0), LocalDateTime.of(2025, 8, 20, 19, 0)),
                new Event(5L, "Fish Tanks Expo", "Show your fish tanks!", "Miami", EventType.OTHER, List.of(PetType.OTHER), LocalDateTime.of(2025, 9, 5, 10, 0), LocalDateTime.of(2025, 9, 5, 16, 0)),
                new Event(6L, "Pet Health Workshop", "Workshop on pet health", "Dallas", EventType.WORKSHOP, List.of(PetType.DOG), LocalDateTime.of(2025, 10, 10, 13, 0), LocalDateTime.of(2025, 10, 10, 15, 0)),
                new Event(7L, "Pet Training Competition", "A competition for best trained pets", "Austin", EventType.COMPETITION, List.of(PetType.DOG), LocalDateTime.of(2025, 11, 15, 9, 0), LocalDateTime.of(2025, 11, 15, 17, 0)),
                new Event(8L, "Cat Health Fair", "A fair for the health of cats", "Seattle", EventType.FAIR, List.of(PetType.CAT), LocalDateTime.of(2025, 12, 1, 10, 0), LocalDateTime.of(2025, 12, 1, 18, 0)),
                new Event(9L, "Pet Costume Contest", "Costume contest for pets", "Las Vegas", EventType.COMPETITION, List.of(PetType.DOG, PetType.CAT), LocalDateTime.of(2026, 1, 14, 16, 0), LocalDateTime.of(2026, 1, 14, 20, 0)),
                new Event(10L, "Pet Expo", "Expo for all pet lovers", "Orlando", EventType.FAIR, List.of(PetType.DOG, PetType.CAT), LocalDateTime.of(2026, 2, 25, 10, 0), LocalDateTime.of(2026, 2, 25, 18, 0))
        ));
    }

    @Override
    public List<Event> findAll() {
        return events;
    }

    @Override
    public List<Event> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return events.stream()
        .filter(event -> !event.getStartDate().toLocalDate().isBefore(startDate) &&
        !event.getEndDate().toLocalDate().isAfter(endDate))
        .toList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return events.stream()
        .filter(event -> event.getId().equals(id))
        .findFirst();
    }

    @Override
    public List<Event> findUpcomingEvents() {
        return events.stream()
        .filter(event -> event.getStartDate().isAfter(LocalDateTime.now()))
        .toList();
    }

    @Override
    public List<Event> findByEventType(String eventType) {
        return events.stream()
        .filter(event -> event.getEventType().equalsIgnoreCase(eventType))
        .toList();
    }
}
