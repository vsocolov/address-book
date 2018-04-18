package com.vsocolov.addressbook.service.impl;

import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.service.AddressBookService;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AddressBookServiceImpl implements AddressBookService {

    @Override
    public Optional<AddressBookEntry> search(final List<AddressBookEntry> addressBook,
                                             final Predicate<AddressBookEntry> filterPredicate) {
        return addressBook.stream().filter(filterPredicate).findAny();
    }

    @Override
    public long count(final List<AddressBookEntry> addressBook, final Predicate<AddressBookEntry> filterPredicate) {
        return addressBook.stream()
                .filter(filterPredicate)
                .count();
    }

    @Override
    public Optional<AddressBookEntry> getOldestPerson(final List<AddressBookEntry> addressBook) {
        return addressBook.stream().min(Comparator.comparing(AddressBookEntry::getBirthDate));
    }

    @Override
    public long getDaysDifference(final AddressBookEntry firstEntry, final AddressBookEntry secondEntry) {
        return ChronoUnit.DAYS.between(firstEntry.getBirthDate(), secondEntry.getBirthDate());
    }

}
