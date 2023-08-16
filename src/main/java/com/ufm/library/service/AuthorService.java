package com.ufm.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.AuthorDto;
import com.ufm.library.dto.api.ResponseBody;

public interface AuthorService {
    public ResponseBody getAllAuthors(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getAuthor(Long id);

    public ResponseBody saveAuthor(@Valid AuthorDto authorDto);

    public ResponseBody updateAuthor(@Valid Long id, AuthorDto authorDto);

    public void deleteAuthor(Long id);
}
