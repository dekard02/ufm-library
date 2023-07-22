package com.ufm.library.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.BookLoanRecordItemDto;
import com.ufm.library.entity.BookLoanRecordItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookLoanRecordItemMapper {
    @Mapping(target = "bookLoanRecord", ignore = true)
    @Mapping(target = "bookLoanRecordItemKey", ignore = true)
    @Mapping(target = "locationBook", ignore = true)
    BookLoanRecordItem bookLoanRecordReqDtoToBookLoanRecord(BookLoanRecordItemDto.Request bookLoanRecordDto);

    @Mapping(target = "bookId", source = "locationBook.book.id")
    @Mapping(target = "bookTitle", source = "locationBook.book.title")
    BookLoanRecordItemDto.Response bookLoanRecordTobookLoanRecordResDto(BookLoanRecordItem bookLoanRecord);

}
