package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookLoanRecordDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookLoanRecordController {
    public ResponseEntity<ResponseBody> getAllBookLoanRecords(Predicate predicate, Pageable pageable, Boolean returned);

    public ResponseEntity<ResponseBody> getBookLoanRecord(Long id);

    public ResponseEntity<ResponseBody> createBookLoanRecord(BookLoanRecordDto.Request bookLoanRecordDto);

    public ResponseEntity<ResponseBody> updateBookLoanRecord(Long id,
            BookLoanRecordDto.Request bookLoanRecordDto);

    public ResponseEntity<ResponseBody> deleteBookLoanRecord(Long id);
}
