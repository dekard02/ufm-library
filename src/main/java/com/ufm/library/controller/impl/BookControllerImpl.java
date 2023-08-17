package com.ufm.library.controller.impl;

import static org.springframework.data.domain.Sort.Direction.DESC;

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
import com.ufm.library.controller.BookController;
import com.ufm.library.dto.BookDto.CreateRequest;
import com.ufm.library.dto.BookDto.UpdateRequest;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Book;
import com.ufm.library.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book information management")
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    @GetMapping
    @Operation(summary = "Get all books information")
    public ResponseEntity<ResponseBody> getAllBooks(
            @QuerydslPredicate(root = Book.class) Predicate predicate,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable,
            @Parameter @RequestParam(defaultValue = "", required = false) String search) {
        var response = bookService.getAllBooks(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    @Operation(summary = "Get one book information")
    public ResponseEntity<ResponseBody> getBook(@PathVariable Long id) {
        var response = bookService.getBook(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Save book information")
    public ResponseEntity<ResponseBody> createBook(CreateRequest bookDto) {
        var response = bookService.saveBook(bookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Update book information")
    public ResponseEntity<ResponseBody> updateBook(@PathVariable Long id,
            UpdateRequest bookDto) {
        var response = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Delete book information")
    public ResponseEntity<ResponseBody> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
