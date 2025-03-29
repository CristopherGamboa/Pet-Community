package com.petcommunity.pet_community.models;

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
public class Pet {
    private Long id;
    private String name;
    @Getter(AccessLevel.NONE)
    private PetType type;
    private Long participantId;

    public String getPetType() {
        return type.getDisplayName();
    }
}
