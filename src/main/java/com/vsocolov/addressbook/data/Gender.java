package com.vsocolov.addressbook.data;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE,
    FEMALE,
    UNKNOWN;

    public static Optional<Gender> toEnum(final String input) {
        return Arrays.stream(values())
                .filter(gender -> gender.name().equalsIgnoreCase(input))
                .findFirst();
    }
}
