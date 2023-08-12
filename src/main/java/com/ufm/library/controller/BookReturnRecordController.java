package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookReturnRecordDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookReturnRecordController {
    public ResponseEntity<ResponseBody> getAllBookReturnRecords(Predicate predicate,
            Pageable pageable);

    public ResponseEntity<ResponseBody> getBookReturnRecord(Long id);

    public ResponseEntity<ResponseBody> createBookReturnRecord(BookReturnRecordDto.Request bookReturnRecordDto);

    public ResponseEntity<ResponseBody> updateBookReturnRecord(Long id,
            BookReturnRecordDto.Request bookReturnRecordDto);

    public ResponseEntity<ResponseBody> deleteBookReturnRecord(Long id);
}
