package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.AuthorDto;
import com.ufm.library.dto.api.ResponseBody;

public interface AuthorController {
    public ResponseEntity<ResponseBody> getAllAuthors(Predicate predicate, Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getAuthor(Long id);

    public ResponseEntity<ResponseBody> createAuthor(AuthorDto authorDto);

    public ResponseEntity<ResponseBody> updateAuthor(Long id, AuthorDto authorDto);

    public ResponseEntity<ResponseBody> deleteAuthor(Long id);
}
