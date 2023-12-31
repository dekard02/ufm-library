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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.AuthorController;
import com.ufm.library.dto.AuthorDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Author;
import com.ufm.library.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
@Tag(name = "Author", description = "Author information management")
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    @GetMapping
    @Operation(summary = "Get all authors information")
    public ResponseEntity<ResponseBody> getAllAuthors(
            @QuerydslPredicate(root = Author.class) Predicate predicate,
            @ParameterObject @PageableDefault Pageable pageable,
            @Parameter @RequestParam(defaultValue = "", required = false) String search) {
        var response = authorService.getAllAuthors(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    @Operation(summary = "Get one author information")
    public ResponseEntity<ResponseBody> getAuthor(@PathVariable Long id) {
        var response = authorService.getAuthor(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Save author information")
    public ResponseEntity<ResponseBody> createAuthor(@RequestBody AuthorDto authorDto) {
        var response = authorService.saveAuthor(authorDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Update author information")
    public ResponseEntity<ResponseBody> updateAuthor(@PathVariable Long id,
            @RequestBody AuthorDto authorDto) {
        var response = authorService.updateAuthor(id, authorDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Delete author information")
    public ResponseEntity<ResponseBody> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
