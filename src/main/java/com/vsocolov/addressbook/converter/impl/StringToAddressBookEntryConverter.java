package com.vsocolov.addressbook.converter.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class StringToAddressBookEntryConverter<T extends String> implements AddressBookEntryConverter<T> {
    private static final Logger LOG = Logger.getLogger(StringToAddressBookEntryConverter.class);
    private static final String COMMA = ",";
    private static final String DATE_FORMAT = "dd/MM/yy";

    private final SimpleDateFormat formatter;

    public StringToAddressBookEntryConverter() {
        formatter = new SimpleDateFormat(DATE_FORMAT);
    }

    @Override
    public Optional<AddressBookEntry> convert(final String source) {
        try {
            final String[] values = source.split(COMMA);
            final String name = values[0].trim();
            final Gender gender = Gender.toEnum(values[1].trim()).orElse(Gender.UNKNOWN);
            final Date birthDate = formatter.parse(values[2].trim());

            return Optional.of(new AddressBookEntry(name, gender, birthDate));
        } catch (final Exception e) {
            LOG.error("Cannot parse input string and extract address book entry.");
        }

        return Optional.empty();
    }
}
