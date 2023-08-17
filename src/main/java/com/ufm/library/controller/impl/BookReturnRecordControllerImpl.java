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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.BookReturnRecordController;
import com.ufm.library.dto.BookReturnRecordDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.BookReturnRecord;
import com.ufm.library.service.BookReturnRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/v1/book-return-records")
@RequiredArgsConstructor
@Tag(name = "Book Return Record", description = "Book return record management")
@SecurityRequirement(name = "JWT")
public class BookReturnRecordControllerImpl implements BookReturnRecordController {

    private final BookReturnRecordService bookReturnRecordService;

    @Override
    @GetMapping
    @Operation(summary = "Get all book return records information")
    public ResponseEntity<ResponseBody> getAllBookReturnRecords(
            @QuerydslPredicate(root = BookReturnRecord.class) Predicate predicate,
            @ParameterObject @PageableDefault(sort = "returnDate", direction = DESC) Pageable pageable) {
        var response = bookReturnRecordService.getAllBookReturnRecords(predicate, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    @Operation(summary = "Get one book return record information")
    public ResponseEntity<ResponseBody> getBookReturnRecord(@PathVariable Long id) {
        var response = bookReturnRecordService.getBookReturnRecord(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Save book return record information")
    public ResponseEntity<ResponseBody> createBookReturnRecord(
            @RequestBody BookReturnRecordDto.Request bookReturnRecordDto) {
        var response = bookReturnRecordService.saveBookReturnRecord(bookReturnRecordDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Update book return record information")
    public ResponseEntity<ResponseBody> updateBookReturnRecord(@PathVariable Long id,
            @RequestBody BookReturnRecordDto.Request bookReturnRecordDto) {
        var response = bookReturnRecordService.updateBookReturnRecord(id, bookReturnRecordDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    @Operation(summary = "Delete book return record information")
    public ResponseEntity<ResponseBody> deleteBookReturnRecord(@PathVariable Long id) {
        bookReturnRecordService.deleteBookReturnRecord(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
