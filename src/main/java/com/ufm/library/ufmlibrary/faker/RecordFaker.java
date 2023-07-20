package com.ufm.library.ufmlibrary.faker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.ufmlibrary.entity.BookLoanRecord;
import com.ufm.library.ufmlibrary.entity.BookLoanRecordItem;
import com.ufm.library.ufmlibrary.entity.BookReturnRecord;
import com.ufm.library.ufmlibrary.entity.Librarian;
import com.ufm.library.ufmlibrary.entity.key.BookLoanRecordItemKey;
import com.ufm.library.ufmlibrary.repository.BookLoanRecordItemRepository;
import com.ufm.library.ufmlibrary.repository.BookLoanRecordRepository;
import com.ufm.library.ufmlibrary.repository.BookReturnRecordRepository;
import com.ufm.library.ufmlibrary.repository.LibrarianRepository;
import com.ufm.library.ufmlibrary.repository.LocationBookRepository;
import com.ufm.library.ufmlibrary.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordFaker {

    private final Faker faker;
    private final LocationBookRepository locationBookRepository;
    private final BookLoanRecordRepository bookLoanRecordRepository;
    private final BookLoanRecordItemRepository bookLoanRecordItemRepository;
    private final BookReturnRecordRepository bookReturnRecordRepository;
    private final StudentRepository studentRepository;
    private final LibrarianRepository librarianRepository;

    private List<BookLoanRecord> bookLoanRecordList;
    private List<Librarian> librarianList;

    public void fake() {
        fakeBookLoanRecord();
        fakeBookReturnRecord();
    }

    private <T> T getRandomFromList(List<T> list) {
        var index = faker.number().numberBetween(0, list.size());
        return list.get(index);
    }

    private void fakeBookLoanRecord() {
        this.bookLoanRecordList = new ArrayList<>();

        this.librarianList = librarianRepository.findAll();
        var students = studentRepository.findAll();
        var locationBooks = locationBookRepository.findAll();
        var locationIds = List.of(1L, 2L, 3L);

        for (int i = 0; i < 35; i++) {
            var librarian = getRandomFromList(librarianList);
            var student = getRandomFromList(students);

            var bookLoanRecord = new BookLoanRecord(null, null, librarian, student, null, null);
            bookLoanRecordRepository.save(bookLoanRecord);
            bookLoanRecordList.add(bookLoanRecord);

            var numOfLoaningBook = faker.number().numberBetween(1, 2);
            for (int j = 0; j < numOfLoaningBook; j++) {
                var locationId = getRandomFromList(locationIds);
                var bookFromOneLocation = locationBooks
                        .stream()
                        .filter(locationBook -> {
                            return locationBook.getLocationBookKey().getLocationId() == locationId;
                        })
                        .collect(Collectors.toList());
                var locationBook = getRandomFromList(bookFromOneLocation);

                bookLoanRecordItemRepository.save(
                        new BookLoanRecordItem(new BookLoanRecordItemKey(),
                                1, null,
                                locationBook,
                                bookLoanRecord));
            }
        }
    }

    private void fakeBookReturnRecord() {
        this.bookLoanRecordList.forEach(bookLoanRecord -> {
            var librarian = getRandomFromList(librarianList);
            var bookReturnRecord = new BookReturnRecord(null, null,
                    bookLoanRecord,
                    librarian,
                    0D, null);

            bookReturnRecordRepository.save(bookReturnRecord);
        });
    }
}
