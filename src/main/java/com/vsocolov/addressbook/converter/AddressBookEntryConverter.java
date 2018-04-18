package com.vsocolov.addressbook.converter;

import com.vsocolov.addressbook.data.AddressBookEntry;

import java.io.Serializable;
import java.util.Optional;

public interface AddressBookEntryConverter<T extends Serializable> {

    Optional<AddressBookEntry> convert(T source);
}
