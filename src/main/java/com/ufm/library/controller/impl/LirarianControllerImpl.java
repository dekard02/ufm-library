package com.ufm.library.controller.impl;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@PreAuthorize("hasRole('MANAGER')")
@RequestMapping("api/v1/librarians")
@RequiredArgsConstructor
@Tag(name = "Librarian", description = "Librarian information management")
@SecurityRequirement(name = "JWT")
public class LirarianControllerImpl implements LibrarianController {

    private final LibrarianService librarianService;

    @Override
    @GetMapping
    @Operation(summary = "Get all librarians information")
    public ResponseEntity<ResponseBody> getAllLibrarians(
            @QuerydslPredicate(root = Librarian.class) Predicate predicate,
            @ParameterObject @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = librarianService.getAllLibrarians(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    @Operation(summary = "Get one librarian information")
    public ResponseEntity<ResponseBody> getLibrarian(@PathVariable Long id) {
        var response = librarianService.getLibrarian(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Operation(summary = "Save librarian information")
    public ResponseEntity<ResponseBody> createLibrarian(LibrarianDto.Request librarianDto) {
        var response = librarianService.saveLibrarian(librarianDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @Operation(summary = "Update librarian information")
    public ResponseEntity<ResponseBody> updateLibrarian(@PathVariable Long id,
            LibrarianDto.Request librarianDto) {
        var response = librarianService.updateLibrarian(id, librarianDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @Operation(summary = "Delete librarian information")
    public ResponseEntity<ResponseBody> deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
