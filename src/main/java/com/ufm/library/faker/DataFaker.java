package com.ufm.library.faker;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufm.library.entity.Author;
import com.ufm.library.entity.Category;
import com.ufm.library.repository.AuthorRepository;
import com.ufm.library.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataFaker {

    private final AuthorRepository authorRepo;
    private final CategoryRepository categoryRepo;
    private final BookFaker bookFaker;
    private final StudentFaker studentFaker;
    private final LibrarianFaker librarianFaker;
    private final RecordFaker recordFaker;

    public void fake() {
        fakeAuthor();
        fakeCategory();
        bookFaker.fake();
        studentFaker.fake();
        librarianFaker.fake();
        recordFaker.fake();
    }

    private void fakeAuthor() {
        var authors = List.of(
                Author.builder().fullname("Yuval Noah Harari").build(), // 1
                Author.builder().fullname("Dale Carnegie").build(), // 2
                Author.builder().fullname("Paulo Coelho").build(), // 3
                Author.builder().fullname("Sasaki Fumio").build(), // 4
                Author.builder().fullname("Napoleon Hill").build(), // 5
                Author.builder().fullname("Steven Covey").build(), // 6
                Author.builder().fullname("Nick Vujic").build(), // 7
                Author.builder().fullname("Rosie Nguyễn").build(), // 8
                Author.builder().fullname("Nguyễn Nhật Ánh").build() // 9
        );
        authorRepo.saveAll(authors);
    }

    private void fakeCategory() {
        var categories = List.of(
                Category.builder().name("Tiếng Việt").build(), // 1
                Category.builder().name("Tiếng Anh").build(), // 2
                Category.builder().name("Việt Nam").build(), // 3
                Category.builder().name("Nước ngoài").build(), // 4
                Category.builder().name("Chính trị").build(), // 5
                Category.builder().name("Tâm linh").build(), // 6
                Category.builder().name("Văn học").build(), // 7
                Category.builder().name("Truyện").build(), // 8
                Category.builder().name("Tiểu thuyết").build(), // 9
                Category.builder().name("Văn hóa").build(), // 10
                Category.builder().name("Lịch sử").build(), // 11
                Category.builder().name("Kinh tế").build(), // 12
                Category.builder().name("Khoa học").build(), // 13
                Category.builder().name("Giáo trình").build() // 14
        );
        categoryRepo.saveAll(categories);
    }
}
