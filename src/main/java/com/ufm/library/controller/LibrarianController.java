package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LibrarianController {
    public ResponseEntity<ResponseBody> getAllLibrarians(Predicate predicate,
            Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getLibrarian(Long id);

    public ResponseEntity<ResponseBody> createLibrarian(LibrarianDto.Request librarianDto);

    public ResponseEntity<ResponseBody> updateLibrarian(Long id,
            LibrarianDto.Request librarianDto);

    public ResponseEntity<ResponseBody> deleteLibrarian(Long id);
}
