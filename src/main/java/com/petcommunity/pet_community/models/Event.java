package com.petcommunity.pet_community.models;

import java.time.LocalDateTime;
import java.util.List;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Event {
    private Long id;
    private String name;
    private String description;
    private String location;
    @Getter(AccessLevel.NONE)
    private EventType eventType;
    @Getter(AccessLevel.NONE)
    private List<PetType> petType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public String getEventType() {
        return eventType.getDisplayName();
    }

    public List<String> getPetType() {
        List<String> petTypes = petType.stream()
        .map(PetType::getDisplayName).toList();

        return petTypes;
    }
}
