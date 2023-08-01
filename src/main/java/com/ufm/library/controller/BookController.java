package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookController {
    public ResponseEntity<ResponseBody> getAllBooks(Predicate predicate, Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getBook(Long id);

    public ResponseEntity<ResponseBody> createBook(BookDto.CreateRequest bookDto);

    public ResponseEntity<ResponseBody> updateBook(Long id, BookDto.UpdateRequest bookDto);

    public ResponseEntity<ResponseBody> deleteBook(Long id);
}
