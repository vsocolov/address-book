package com.vsocolov.addressbook.converter.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class StringToAddressBookEntryConverter<T extends String> implements AddressBookEntryConverter<T> {
    private static final Logger LOG = Logger.getLogger(StringToAddressBookEntryConverter.class);
    private static final String COMMA = ",";
    private static final String DATE_FORMAT = "dd/MM/";

    private final DateTimeFormatter formatter;

    public StringToAddressBookEntryConverter() {
        // used builder because in new java 8 Date API, two digit dates starts from 2000 year, but we need from 1900
        formatter = new DateTimeFormatterBuilder().appendPattern(DATE_FORMAT)
                .appendValueReduced(ChronoField.YEAR, 2, 2, Year.now().getValue() - 80)
                .toFormatter();
    }

    @Override
    public Optional<AddressBookEntry> convert(final String source) {
        try {
            final String[] values = source.split(COMMA);
            final String name = values[0].trim();
            final Gender gender = Gender.toEnum(values[1].trim()).orElse(Gender.UNKNOWN);
            final LocalDate birthDate = LocalDate.parse(values[2].trim(), formatter);

            return Optional.of(new AddressBookEntry(name, gender, birthDate));
        } catch (final Exception e) {
            LOG.error("Cannot parse input string and extract address book entry.");
        }

        return Optional.empty();
    }
}
