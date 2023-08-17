package com.ufm.library.controller.impl;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import com.ufm.library.controller.BookLoanRecordController;
import com.ufm.library.dto.BookLoanRecordDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.service.BookLoanRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/v1/book-loan-records")
@RequiredArgsConstructor
@Tag(name = "Book Loan Record", description = "Book loan record management")
@SecurityRequirement(name = "JWT")
public class BookLoanRecordControllerImpl implements BookLoanRecordController {

    private final BookLoanRecordService bookLoanRecordService;

    @Override
    @GetMapping
    @Operation(summary = "Get all book loan records information")
    public ResponseEntity<ResponseBody> getAllBookLoanRecords(
            @QuerydslPredicate(root = BookLoanRecord.class) Predicate predicate,
            @ParameterObject @PageableDefault(sort = "loanDate", direction = Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Boolean returned) {
        var response = bookLoanRecordService.getAllBookLoanRecords(predicate, pageable, returned);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    @Operation(summary = "Get one book loan record information")
    public ResponseEntity<ResponseBody> getBookLoanRecord(@PathVariable Long id) {
        var response = bookLoanRecordService.getBookLoanRecord(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Save book loan record information")
    public ResponseEntity<ResponseBody> createBookLoanRecord(
            @RequestBody BookLoanRecordDto.Request bookLoanRecordDto) {
        var response = bookLoanRecordService.saveBookLoanRecord(bookLoanRecordDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Update book loan record information")
    public ResponseEntity<ResponseBody> updateBookLoanRecord(@PathVariable Long id,
            @RequestBody BookLoanRecordDto.Request bookLoanRecordDto) {
        var response = bookLoanRecordService.updateBookLoanRecord(id, bookLoanRecordDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Delete book loan record information")
    public ResponseEntity<ResponseBody> deleteBookLoanRecord(@PathVariable Long id) {
        bookLoanRecordService.deleteBookLoanRecord(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
