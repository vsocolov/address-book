package com.vsocolov.addressbook.converter.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringToAddressBookEntryConverterTest {

    private final AddressBookEntryConverter<String> converter = new StringToAddressBookEntryConverter<>();

    @Test
    public void convert_should_return_addressbook_entry_if_input_is_valid() {
        final String source = "Bill McKnight, Male, 16/03/77";
        final Calendar expectedBirthDate = Calendar.getInstance();
        expectedBirthDate.set(1977, Calendar.MARCH, 16);

        final Optional<AddressBookEntry> result = converter.convert(source);
        assertThat(result.isPresent(), equalTo(true));
        assertThat(result.get().getName(), equalTo("Bill McKnight"));
        assertThat(result.get().getGender(), equalTo(Gender.MALE));
        assertBirthDates(result.get().getBirthDate(), expectedBirthDate.getTime());
    }

    @Test
    public void convert_should_return_optional_if_input_is_invalid() {
        final Optional<AddressBookEntry> result = converter.convert("asdfasdfasfd");
        assertThat(result, equalTo(Optional.empty()));
    }

    private void assertBirthDates(final Date dateFirst, final Date dateSecond) {
        final Calendar first = Calendar.getInstance();
        first.setTime(dateFirst);
        final Calendar second = Calendar.getInstance();
        second.setTime(dateSecond);

        assertThat(first.get(Calendar.YEAR), equalTo(second.get(Calendar.YEAR)));
        assertThat(first.get(Calendar.MONTH), equalTo(second.get(Calendar.MONTH)));
        assertThat(first.get(Calendar.DATE), equalTo(second.get(Calendar.DATE)));
    }


}