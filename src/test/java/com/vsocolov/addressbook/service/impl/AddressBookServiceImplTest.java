package com.vsocolov.addressbook.service.impl;

import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.service.AddressBookService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.vsocolov.addressbook.data.Gender.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressBookServiceImplTest {

    private final AddressBookService service = new AddressBookServiceImpl();

    @Test
    public void search_should_search_entries_by_name() {
        final List<AddressBookEntry> addressBook = addressBookStub();
        final Predicate<AddressBookEntry> searchPredicate = entry -> entry.getName().equals("Sarah Stone");

        final Optional<AddressBookEntry> entry = service.search(addressBook, searchPredicate);

        assertThat(entry.isPresent(), equalTo(true));
        assertThat(entry.get().getName(), equalTo("Sarah Stone"));
    }

    @Test
    public void search_should_return_optional_empty_if_no_result_found() {
        final List<AddressBookEntry> addressBook = addressBookStub();
        final Predicate<AddressBookEntry> searchPredicate = entry -> entry.getName().equals("xyz");

        final Optional<AddressBookEntry> entry = service.search(addressBook, searchPredicate);

        assertThat(entry.isPresent(), equalTo(false));
    }

    @Test
    public void count_should_return_only_male_entries() {
        final List<AddressBookEntry> addressBook = addressBookStub();
        final Predicate<AddressBookEntry> filterPredicate = entry -> entry.getGender() == MALE;

        final long result = service.count(addressBook, filterPredicate);

        assertThat(result, equalTo(3L));
    }

    @Test
    public void count_should_return_0_if_we_filter_by_unknow_gender() {
        final List<AddressBookEntry> addressBook = addressBookStub();
        final Predicate<AddressBookEntry> filterPredicate = entry -> entry.getGender() == UNKNOWN;

        final long result = service.count(addressBook, filterPredicate);

        assertThat(result, equalTo(0L));
    }

    @Test
    public void getOldestPerson_should_return_oldest_person_in_addressbook() {
        final List<AddressBookEntry> addressBook = addressBookStub();

        final Optional<AddressBookEntry> oldestPerson = service.getOldestPerson(addressBook);

        assertThat(oldestPerson.isPresent(), equalTo(true));
        assertThat(oldestPerson.get().getName(), equalTo("Wes Jackson"));
    }

    @Test
    public void getDaysDifference_should_return_22_days_difference_between_bill_and_paul() {
        final AddressBookEntry bill = new AddressBookEntry("Bill McKnight", MALE, LocalDate.of(1977, 3, 2));
        final AddressBookEntry paul = new AddressBookEntry("Paul Robinson", MALE, LocalDate.of(1977, 3, 24));

        long daysDifference = service.getDaysDifference(bill, paul);
        assertThat(daysDifference, equalTo(22L));
    }

    private List<AddressBookEntry> addressBookStub() {
        final AddressBookEntry bill = new AddressBookEntry("Bill McKnight", MALE, LocalDate.of(1977, 3, 16));
        final AddressBookEntry paul = new AddressBookEntry("Paul Robinson", MALE, LocalDate.of(1985, 1, 15));
        final AddressBookEntry gemma = new AddressBookEntry("Gemma Lane", FEMALE, LocalDate.of(1991, 11, 20));
        final AddressBookEntry sarah = new AddressBookEntry("Sarah Stone", FEMALE, LocalDate.of(1980, 9, 20));
        final AddressBookEntry wes = new AddressBookEntry("Wes Jackson", MALE, LocalDate.of(1974, 8, 14));

        return Arrays.asList(bill, paul, gemma, sarah, wes);
    }
}