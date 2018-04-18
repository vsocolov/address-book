package com.vsocolov.addressbook.data;

import java.io.Serializable;
import java.util.Date;

public class AddressBookEntry implements Serializable {

    private final String name;

    private final Gender gender;

    private final Date birthDate;

    public AddressBookEntry(final String name, final Gender gender, final Date birthDate) {
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

    public Date getBirthDate() {
        return birthDate;
    }
}
