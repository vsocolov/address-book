package com.vsocolov.addressbook;

import com.vsocolov.addressbook.data.AddressBookEntry;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class AddressBookTemplate {

    private static final String INPUT_FILE_PATH = "src/main/resources/AddressBook";

    protected abstract List<AddressBookEntry> extractEntries(Path path);

    protected abstract long extractMaleCounts(List<AddressBookEntry> entries);

    protected abstract Optional<AddressBookEntry> extractOldestPerson(List<AddressBookEntry> entries);

    protected abstract long extractBillAndPaulDaysDifference(AddressBookEntry bill, AddressBookEntry paul);

    protected abstract Optional<AddressBookEntry> searchEntryByName(List<AddressBookEntry> entries, String name);

    public void printResults() {
        final List<AddressBookEntry> entries = extractEntries(Paths.get(INPUT_FILE_PATH));

        long maleCounts = extractMaleCounts(entries);
        System.out.println("Male entries in the address book: " + maleCounts);

        final Optional<AddressBookEntry> oldestPerson = extractOldestPerson(entries);
        oldestPerson.ifPresent(person -> System.out.println("Oldestperson in the address book: " + person.getName()));

        final Optional<AddressBookEntry> bill = searchEntryByName(entries, "Bill McKnight");
        final Optional<AddressBookEntry> paul = searchEntryByName(entries, "Paul Robinson");
        if (bill.isPresent() && paul.isPresent()) {
            System.out.println("Bill and Paul days difference: " + extractBillAndPaulDaysDifference(bill.get(), paul.get()));
        }
    }
}
