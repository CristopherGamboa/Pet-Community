package com.petcommunity.pet_community.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.petcommunity.pet_community.models.EventParticipant;
import com.petcommunity.pet_community.repositories.interfaces.IEventParticipantRepository;

@Repository
public class EventParticipantRepository implements IEventParticipantRepository {
    private final List<EventParticipant> eventParticipants;

    public EventParticipantRepository() {
        eventParticipants = new ArrayList<>(List.of(
            new EventParticipant(1L, 2L, 6L),
            new EventParticipant(2L, 5L, 1L),
            new EventParticipant(3L, 5L, 2L),
            new EventParticipant(4L, 9L, 10L),
            new EventParticipant(5L, 9L, 8L),
            new EventParticipant(6L, 9L, 7L),
            new EventParticipant(7L, 3L, 8L),
            new EventParticipant(8L, 3L, 9L),
            new EventParticipant(9L, 3L, 6L),
            new EventParticipant(10L, 3L, 7L),
            new EventParticipant(11L, 7L, 3L),
            new EventParticipant(12L, 7L, 2L),
            new EventParticipant(13L, 7L, 1L),
            new EventParticipant(14L, 7L, 4L),
            new EventParticipant(15L, 7L, 5L),
            new EventParticipant(16L, 1L, 4L),
            new EventParticipant(17L, 1L, 5L),
            new EventParticipant(18L, 1L, 6L),
            new EventParticipant(19L, 1L, 7L),
            new EventParticipant(20L, 1L, 8L),
            new EventParticipant(21L, 1L, 9L),
            new EventParticipant(22L, 10L, 9L),
            new EventParticipant(23L, 10L, 8L),
            new EventParticipant(24L, 10L, 7L),
            new EventParticipant(25L, 10L, 6L),
            new EventParticipant(26L, 10L, 5L),
            new EventParticipant(27L, 10L, 4L),
            new EventParticipant(28L, 10L, 3L),
            new EventParticipant(29L, 6L, 2L),
            new EventParticipant(30L, 6L, 1L),
            new EventParticipant(31L, 6L, 5L),
            new EventParticipant(32L, 6L, 4L),
            new EventParticipant(33L, 6L, 6L),
            new EventParticipant(34L, 6L, 7L),
            new EventParticipant(35L, 6L, 8L),
            new EventParticipant(36L, 6L, 9L),
            new EventParticipant(37L, 4L, 7L),
            new EventParticipant(38L, 4L, 6L),
            new EventParticipant(39L, 4L, 5L),
            new EventParticipant(40L, 4L, 4L),
            new EventParticipant(41L, 4L, 3L),
            new EventParticipant(42L, 4L, 2L),
            new EventParticipant(43L, 4L, 1L),
            new EventParticipant(44L, 4L, 8L),
            new EventParticipant(45L, 4L, 9L),
            new EventParticipant(46L, 8L, 5L),
            new EventParticipant(47L, 8L, 4L),
            new EventParticipant(48L, 8L, 3L),
            new EventParticipant(49L, 8L, 2L),
            new EventParticipant(50L, 8L, 1L),
            new EventParticipant(51L, 8L, 6L),
            new EventParticipant(52L, 8L, 7L),
            new EventParticipant(53L, 8L, 8L),
            new EventParticipant(54L, 8L, 9L),
            new EventParticipant(55L, 8L, 10L)
        ));
    }

    @Override
    public Long countParticipantsByEventId(Long id) {
        return eventParticipants.stream()
        .filter(ep -> ep.getEventId() == id)
        .count();
    }

    @Override
    public List<EventParticipant> findByEventId(Long id) {
        return eventParticipants.stream()
        .filter(ep -> ep.getEventId() == id)
        .toList();
    }

    @Override
    public List<EventParticipant> findByParticipantId(Long id) {
        return eventParticipants.stream()
        .filter(ep -> ep.getParticipantId() == id)
        .toList();
    }
}
