package com.petcommunity.pet_community.dtos;

import jakarta.validation.constraints.NotNull;
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
public class EventParticipantRequest {
    @NotNull(message = "Event id is required")
    private Long eventId;

    @NotNull(message = "Participant id is required")
    private Long participantId;
}
