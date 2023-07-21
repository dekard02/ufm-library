package com.ufm.library.faker;

import org.springframework.stereotype.Service;

import com.ufm.library.entity.Author;
import com.ufm.library.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataFaker {

    private final AuthorRepository authorRepository;
    private final BookFaker bookFaker;
    private final StudentFaker studentFaker;
    private final LibrarianFaker librarianFaker;
    private final RecordFaker recordFaker;

    public void fake() {
        fakeAuthor();
        bookFaker.fake();
        // studentFaker.fake();
        // librarianFaker.fake();
        // recordFaker.fake();
    }

    private void fakeAuthor() {
        authorRepository.save(Author.builder().fullname("Yuval Noah Harari").build()); // 1
        authorRepository.save(Author.builder().fullname("Dale Carnegie").build());// 2
        authorRepository.save(Author.builder().fullname("Paulo Coelho").build());// 3
        authorRepository.save(Author.builder().fullname("Sasaki Fumio").build());// 4
        authorRepository.save(Author.builder().fullname("Napoleon Hill").build());// 5
        authorRepository.save(Author.builder().fullname("Steven Covey").build());// 6
        authorRepository.save(Author.builder().fullname("Nick Vujic").build());// 7
        authorRepository.save(Author.builder().fullname("Rosie Nguyễn").build());// 8
        authorRepository.save(Author.builder().fullname("Nguyễn Nhật Ánh").build());// 9
    }
}
