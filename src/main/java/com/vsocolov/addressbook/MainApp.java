package com.vsocolov.addressbook;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.converter.impl.StringToAddressBookEntryConverter;
import com.vsocolov.addressbook.inputreader.InputReader;
import com.vsocolov.addressbook.inputreader.impl.TextFileInputReader;
import com.vsocolov.addressbook.service.AddressBookService;
import com.vsocolov.addressbook.service.impl.AddressBookServiceImpl;

public class MainApp {

    public static void main(final String[] args) {
        final AddressBookEntryConverter<String> converter = new StringToAddressBookEntryConverter<>();
        final InputReader inputReader = new TextFileInputReader(converter);
        final AddressBookService addressBookService = new AddressBookServiceImpl();

        final AddressBookTemplate addressBook = new AddressBook(inputReader, addressBookService);

        addressBook.printResults();
    }
}
