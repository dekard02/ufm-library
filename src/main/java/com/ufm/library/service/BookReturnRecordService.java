package com.ufm.library.service;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookReturnRecordDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookReturnRecordService {
    public ResponseBody getAllBookReturnRecords(Predicate predicate, Pageable pageable);

    public ResponseBody getBookReturnRecord(Long id);

    public ResponseBody saveBookReturnRecord(BookReturnRecordDto.Request bookReturnRecordDto);

    public ResponseBody updateBookReturnRecord(Long id,
            BookReturnRecordDto.Request bookReturnRecordDto);

    public void deleteBookReturnRecord(Long id);
}
