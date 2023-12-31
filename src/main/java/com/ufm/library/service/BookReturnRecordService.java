package com.ufm.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.BookReturnRecordDto;
import com.ufm.library.dto.api.ResponseBody;

public interface BookReturnRecordService {
    public ResponseBody getAllBookReturnRecords(Predicate predicate, Pageable pageable);

    public ResponseBody getBookReturnRecord(Long id);

    public ResponseBody saveBookReturnRecord(
            @Valid BookReturnRecordDto.Request bookReturnRecordDto);

    public ResponseBody updateBookReturnRecord(Long id,
            @Valid BookReturnRecordDto.Request bookReturnRecordDto);

    public void deleteBookReturnRecord(Long id);
}
