package com.ufm.library.service;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookLoanRecordDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookLoanRecordService {
    public ResponseBody getAllBookLoanRecords(Predicate predicate, Pageable pageable, Boolean returned);

    public ResponseBody getBookLoanRecord(Long id);

    public ResponseBody saveBookLoanRecord(BookLoanRecordDto.Request bookLoanRecordDto);

    public ResponseBody updateBookLoanRecord(Long id,
            BookLoanRecordDto.Request bookLoanRecordDto);

    public void deleteBookLoanRecord(Long id);
}
