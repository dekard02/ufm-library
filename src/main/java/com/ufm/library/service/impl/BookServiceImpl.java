package com.ufm.library.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Predicate;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.dto.BookDto.CreateRequest;
import com.ufm.library.dto.BookDto.UpdateRequest;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.BookMapper;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.BookPhoto;
import com.ufm.library.entity.QBook;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.FilenameHelper;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.AuthorRepository;
import com.ufm.library.repository.BookPhotoRepository;
import com.ufm.library.repository.BookRepository;
import com.ufm.library.repository.CategoryRepository;
import com.ufm.library.service.BookService;
import com.ufm.library.service.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final CategoryRepository categoryRepo;
    private final AuthorRepository authorRepo;
    private final BookPhotoRepository bookPhotoRepo;
    private final BookMapper bookMapper;
    private final FilenameHelper filenameHelper;
    private final ResponseBodyHelper responseBodyHelper;
    private final StorageService storageService;

    @Override
    public ResponseBody getAllBooks(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QBook.book.title
                .concat(QBook.book.slug)
                .concat(QBook.book.summary)
                .containsIgnoreCase(search)
                .and(predicate);
        var bookPage = bookRepo.findAll(searchPredicate, pageable);
        var books = bookPage.getContent()
                .stream()
                .map(bookMapper::bookToBookResDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(bookPage, "books", books);
    }

    @Override
    public ResponseBody getBook(Long id) {
        var bookDto = bookRepo.findById(id)
                .map(bookMapper::bookToBookDetailResDto)
                .orElseThrow(() -> new ApplicationException(
                        "Không tìm thấy sách với mã " + id, HttpStatus.NOT_FOUND));
        return responseBodyHelper.success("book", bookDto);
    }

    private void saveImages(Book book, List<MultipartFile> photos) {
        book.setPhotos(new ArrayList<BookPhoto>());
        photos.forEach((file) -> {
            try {
                var extension = filenameHelper.getMultipartFileExtension(file);
                var photoName = UUID.randomUUID().toString() + "." + extension;
                var inputStream = file.getInputStream();
                var photo = storageService.store(inputStream, photoName,
                        StorageContants.BOOK_IMAGES_FOLDER);
                var bookPhoto = BookPhoto.builder()
                        .book(book)
                        .directory(photo)
                        .build();
                book.getPhotos().add(bookPhoto);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bookPhotoRepo.saveAll(book.getPhotos());
    }

    @Override
    @Transactional
    public ResponseBody saveBook(CreateRequest bookDto) {
        var book = bookMapper.bookCreateReqDtoToBook(bookDto, categoryRepo, authorRepo);

        book.setId(null);
        saveImages(book, bookDto.getPhotos());

        bookRepo.save(book);
        return responseBodyHelper
                .success("book", bookMapper.bookToBookDetailResDto(book));
    }

    @Override
    @Transactional
    public ResponseBody updateBook(Long id, UpdateRequest bookDto) {
        var oldBook = bookRepo
                .findById(id)
                .orElseThrow(
                        () -> new ApplicationException("Không tìm thấy sách với mã " + id,
                                HttpStatus.NOT_FOUND));

        var book = bookMapper.bookUpdateReqDtoToBook(bookDto, categoryRepo, authorRepo);

        book.setId(id);
        if (bookDto.getPhotos() != null) {
            oldBook.getPhotos().forEach((photo) -> {
                storageService.delete(photo.getDirectory());
                bookPhotoRepo.delete(photo);
            });
            saveImages(book, bookDto.getPhotos());
        } else {
            book.setPhotos(oldBook.getPhotos());
        }

        bookRepo.save(book);
        return responseBodyHelper
                .success("book", bookMapper.bookToBookDetailResDto(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepo.findById(id).ifPresentOrElse(
                (book) -> {
                    book.setIsDeleted(true);
                    bookRepo.save(book);
                },
                () -> {
                    throw new ApplicationException("Không tìm thấy sách với mã " + id,
                            HttpStatus.NOT_FOUND);
                });
    }

}
