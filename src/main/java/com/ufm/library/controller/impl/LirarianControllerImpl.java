package com.ufm.library.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.LibrarianController;
import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Librarian;
import com.ufm.library.service.LibrarianService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/librarians")
@RequiredArgsConstructor
public class LirarianControllerImpl implements LibrarianController {

    private final LibrarianService librarianService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllLibrarians(
            @QuerydslPredicate(root = Librarian.class) Predicate predicate,
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = librarianService.getAllLibrarians(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getLibrarian(@PathVariable Long id) {
        var response = librarianService.getLibrarian(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createLibrarian(LibrarianDto.Request librarianDto) {
        var response = librarianService.saveLibrarian(librarianDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateLibrarian(@PathVariable Long id,
            LibrarianDto.Request librarianDto) {
        var response = librarianService.updateLibrarian(id, librarianDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
