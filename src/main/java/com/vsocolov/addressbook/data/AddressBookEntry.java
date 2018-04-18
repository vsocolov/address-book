package com.vsocolov.addressbook.data;

import java.io.Serializable;
import java.time.LocalDate;

public class AddressBookEntry implements Serializable {

    private final String name;

    private final Gender gender;

    private final LocalDate birthDate;

    public AddressBookEntry(final String name, final Gender gender, final LocalDate birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
