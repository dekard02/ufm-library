package com.ufm.library.service;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookService {
    public ResponseBody getAllBooks(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getBook(Long id);

    public ResponseBody saveBook(BookDto.CreateRequest bookDto);

    public ResponseBody updateBook(Long id, BookDto.UpdateRequest bookDto);

    public void deleteBook(Long id);
}
