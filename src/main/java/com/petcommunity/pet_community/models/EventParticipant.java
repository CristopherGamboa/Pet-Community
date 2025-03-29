package com.petcommunity.pet_community.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EventParticipant {
    private Long id;
    private Long eventId;
    private Long participantId;
}
