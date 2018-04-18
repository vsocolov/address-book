package com.vsocolov.addressbook.inputreader.impl;

import com.vsocolov.addressbook.converter.AddressBookEntryConverter;
import com.vsocolov.addressbook.data.AddressBookEntry;
import com.vsocolov.addressbook.inputreader.InputReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileInputReader implements InputReader {
    private static final Logger LOG = Logger.getLogger(TextFileInputReader.class);

    private final AddressBookEntryConverter<String> addressBookEntryConverter;

    public TextFileInputReader(final AddressBookEntryConverter<String> addressBookEntryConverter) {
        this.addressBookEntryConverter = addressBookEntryConverter;
    }

    @Override
    public List<AddressBookEntry> getEntries(final Path inputPath) {
        try {
            try (final Stream<String> stream = Files.lines(inputPath)) {
                return stream.map(addressBookEntryConverter::convert)
                        .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                        .collect(Collectors.toList());
            }
        } catch (final IOException e) {
            LOG.error("Cannot read input file. Invalid path or corrupted file.");
        }

        return Collections.emptyList();
    }
}
