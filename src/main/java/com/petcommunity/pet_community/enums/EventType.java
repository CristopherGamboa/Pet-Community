package com.petcommunity.pet_community.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    FAIR(1, "Fair"),
    COMPETITION(2, "Competition"),
    WORKSHOP(3, "Workshop"),
    OTHER(4, "Other");

    private static final Map<Integer, EventType> CODE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(EventType::getCode, Function.identity()));

    private final int code;
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

    public static Optional<EventType> fromCode(int code) {
        return Optional.ofNullable(CODE_MAP.get(code));
    }

    public static Optional<EventType> fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(event -> event.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst();
    }
}
