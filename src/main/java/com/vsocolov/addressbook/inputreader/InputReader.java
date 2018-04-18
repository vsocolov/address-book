package com.vsocolov.addressbook.inputreader;

import com.vsocolov.addressbook.data.AddressBookEntry;

import java.nio.file.Path;
import java.util.List;

public interface InputReader {
    List<AddressBookEntry> getEntries(Path inputPath);
}
