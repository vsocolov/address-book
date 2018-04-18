package com.vsocolov.addressbook.converter.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringToAddressBookEntryConverterTest {

    private final AddressBookEntryConverter<String> converter = new StringToAddressBookEntryConverter<>();

    @Test
    public void convert_should_return_addressbook_entry_if_input_is_valid() {
        final String source = "Bill McKnight, Male, 16/03/77";
        final LocalDate expectedBirthDate = LocalDate.of(1977, 3, 16);

        final Optional<AddressBookEntry> result = converter.convert(source);
        assertThat(result.isPresent(), equalTo(true));
        assertThat(result.get().getName(), equalTo("Bill McKnight"));
        assertThat(result.get().getGender(), equalTo(Gender.MALE));
        assertBirthDates(result.get().getBirthDate(), expectedBirthDate);
    }

    @Test
    public void convert_should_return_optional_if_input_is_invalid() {
        final Optional<AddressBookEntry> result = converter.convert("asdfasdfasfd");
        assertThat(result, equalTo(Optional.empty()));
    }

    private void assertBirthDates(final LocalDate firstDate, final LocalDate secondDate) {
        assertThat(firstDate.getYear(), equalTo(secondDate.getYear()));
        assertThat(firstDate.getMonth(), equalTo(secondDate.getMonth()));
        assertThat(firstDate.getDayOfMonth(), equalTo(secondDate.getDayOfMonth()));
    }


}