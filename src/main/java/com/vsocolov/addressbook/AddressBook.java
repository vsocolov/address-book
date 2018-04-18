package com.vsocolov.addressbook;

import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.data.Gender;
import com.vsocolov.addressbook.inputreader.InputReader;
import com.vsocolov.addressbook.service.AddressBookService;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class AddressBook extends AddressBookTemplate {

    private final InputReader inputReader;

    private final AddressBookService addressBookService;

    public AddressBook(final InputReader inputReader, final AddressBookService addressBookService) {
        this.inputReader = inputReader;
        this.addressBookService = addressBookService;
    }

    @Override
    protected List<AddressBookEntry> extractEntries(final Path path) {
        return inputReader.getEntries(path);
    }

    @Override
    protected long extractMaleCounts(final List<AddressBookEntry> entries) {
        return addressBookService.count(entries, entry -> entry.getGender() == Gender.MALE);
    }

    @Override
    protected Optional<AddressBookEntry> extractOldestPerson(final List<AddressBookEntry> entries) {
        return addressBookService.getOldestPerson(entries);
    }

    @Override
    protected long extractBillAndPaulDaysDifference(final AddressBookEntry bill, final AddressBookEntry paul) {
        return addressBookService.getDaysDifference(bill, paul);
    }

    @Override
    protected Optional<AddressBookEntry> searchEntryByName(final List<AddressBookEntry> entries, final String name) {
        return addressBookService.search(entries, entry -> entry.getName().equalsIgnoreCase(name));
    }
}
