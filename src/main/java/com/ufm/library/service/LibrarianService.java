package com.ufm.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LibrarianService {
    public ResponseBody getAllLibrarians(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getLibrarian(Long id);

    public ResponseBody saveLibrarian(@Valid LibrarianDto.Request librarianDto);

    public ResponseBody updateLibrarian(Long id, @Valid LibrarianDto.Request librarianDto);

    public void deleteLibrarian(Long id);
}
