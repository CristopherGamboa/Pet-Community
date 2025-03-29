package com.petcommunity.pet_community.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Participant {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
