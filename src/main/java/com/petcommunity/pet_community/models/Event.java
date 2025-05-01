package com.petcommunity.pet_community.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event extends RepresentationModel<Event> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    @Getter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Getter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private PetType petType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public String getEventType() {
        return eventType.getDisplayName();
    }

    public String getPetType() {
        return petType.getDisplayName();
    }
}
