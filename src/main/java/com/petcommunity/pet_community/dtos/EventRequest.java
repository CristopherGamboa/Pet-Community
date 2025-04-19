package com.petcommunity.pet_community.dtos;

import java.time.LocalDateTime;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class EventRequest {
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Description is required")
    @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters")
    private String description;

    @NotNull(message = "Location is required")
    @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
    private String location;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    @NotNull(message = "Pet type is required")
    private PetType petType;

    @Future(message = "Start date must be in the future")
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    @NotNull(message = "End date is required")
    private LocalDateTime endDate;
}
