package com.vsocolov.addressbook;

import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import com.vsocolov.addressbook.inputreader.InputReader;
import com.vsocolov.addressbook.service.AddressBookService;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {

    private AddressBook addressBook;

    private InputReader inputReader;

    private AddressBookService addressBookService;

    @Before
    public void setUp() {
        inputReader = mock(InputReader.class);
        addressBookService = mock(AddressBookService.class);
        addressBook = new AddressBook(inputReader, addressBookService);
    }

    @Test
    public void extractEntries_should_call_inputReader_method() {
        final Path path = Paths.get("test");
        final List<AddressBookEntry> expectedList = Collections.emptyList();

        when(inputReader.getEntries(path)).thenReturn(expectedList);

        final List<AddressBookEntry> entries = addressBook.extractEntries(path);

        assertThat(entries, sameInstance(expectedList));
    }

    @Test
    public void extractMaleCounts_should_call_count_method_from_addressbook_service() {
        final List<AddressBookEntry> entries = Collections.emptyList();

        when(addressBookService.count(eq(entries), any(Predicate.class))).thenReturn(3L);

        long count = addressBook.extractMaleCounts(entries);
        assertThat(count, equalTo(3L));
    }

    @Test
    public void extractOldestPerson_should_call_getOldestPerson_method_from_addressbook_service() {
        final AddressBookEntry expectedOldestPerson = new AddressBookEntry("John", Gender.MALE, LocalDate.now());
        final List<AddressBookEntry> entries = Collections.singletonList(expectedOldestPerson);

        when(addressBookService.getOldestPerson(entries)).thenReturn(Optional.of(expectedOldestPerson));

        final Optional<AddressBookEntry> oldestPerson = addressBook.extractOldestPerson(entries);
        assertThat(oldestPerson.get(), equalTo(expectedOldestPerson));
    }

    @Test
    public void extractBillAndPaulDaysDifference_should_call_getOldestPerson_method_from_addressbook_service() {
        final AddressBookEntry bill = new AddressBookEntry("Bill", Gender.MALE, LocalDate.now());
        final AddressBookEntry paul = new AddressBookEntry("Paul", Gender.MALE, LocalDate.now());

        when(addressBookService.getDaysDifference(bill, paul)).thenReturn(11L);

        long daysDifference = addressBook.extractBillAndPaulDaysDifference(bill, paul);
        assertThat(daysDifference, equalTo(11L));
    }

    @Test
    public void searchEntryByName_should_call_search_method_from_addressbook_service() {
        final List<AddressBookEntry> entries = Collections.emptyList();
        final AddressBookEntry expectedEntry = new AddressBookEntry("John", Gender.MALE, LocalDate.now());

        when(addressBookService.search(eq(entries), any(Predicate.class))).thenReturn(Optional.of(expectedEntry));

        final Optional<AddressBookEntry> john = addressBook.searchEntryByName(entries, "John");
        assertThat(john.get(), equalTo(expectedEntry));
    }
}