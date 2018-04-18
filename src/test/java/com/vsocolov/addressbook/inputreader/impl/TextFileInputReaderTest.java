package com.vsocolov.addressbook.inputreader.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.inputreader.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.vsocolov.addressbook.data.Gender.MALE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TextFileInputReaderTest {

    private static final String INPUT_SOURCE = "src/test/resources/AddressBook";
    private static final String INPUT_SOURCE_INVALID_PATH = "src/test/resources/invalid";

    private InputReader inputReader;

    private AddressBookEntryConverter<String> addressBookEntryConverter;

    @Before
    public void setUp() {
        addressBookEntryConverter = mock(AddressBookEntryConverter.class);
        inputReader = new TextFileInputReader(addressBookEntryConverter);
    }

    @Test
    public void getEntries_should_return_a_list_of_entries_if_inputh_path_is_valid() {
        final Path path = Paths.get(INPUT_SOURCE);
        final Optional<AddressBookEntry> expectedEntry = Optional.of(new AddressBookEntry("John Doe", MALE, LocalDate.now()));

        when(addressBookEntryConverter.convert(any(String.class))).thenReturn(expectedEntry);

        final List<AddressBookEntry> entries = inputReader.getEntries(path);

        assertThat(entries, hasSize(1));
        assertThat(entries, hasItem(expectedEntry.get()));
    }

    @Test
    public void getEntries_should_return_empty_list_if_path_is_invalid() {
        final Path path = Paths.get(INPUT_SOURCE_INVALID_PATH);

        final List<AddressBookEntry> entries = inputReader.getEntries(path);

        assertThat(entries.isEmpty(), equalTo(true));
        verifyZeroInteractions(addressBookEntryConverter);
    }

    @Test
    public void getEntries_should_return_empty_list_if_converter_returns_optional_empty() {
        final Path path = Paths.get(INPUT_SOURCE);

        when(addressBookEntryConverter.convert(any(String.class))).thenReturn(Optional.empty());

        final List<AddressBookEntry> entries = inputReader.getEntries(path);

        assertThat(entries.isEmpty(), equalTo(true));
    }

}