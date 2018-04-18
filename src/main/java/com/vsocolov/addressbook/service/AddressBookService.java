package com.vsocolov.addressbook.service;

import com.vsocolov.addressbook.data.AddressBookEntry;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AddressBookService {

    long count(List<AddressBookEntry> addressBook, Predicate<AddressBookEntry> filterPredicate);

    Optional<AddressBookEntry> getOldestPerson(List<AddressBookEntry> addressBook);

    long getDaysDifference(AddressBookEntry first, AddressBookEntry second);
}
