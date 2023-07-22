package com.ufm.library.dto.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.ufm.library.dto.BookLoanRecordDto;
import com.ufm.library.dto.BookLoanRecordItemDto;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.entity.BookLoanRecordItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookLoanRecordMapper {
    // @Mapping(target = "books", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "librarian", ignore = true)
    @Mapping(target = "loanDate", ignore = true)
    // @Mapping(target = "loanDate", ignore = true)
    // @Mapping(target = "bookLoanRecordItems", qualifiedByName =
    // "bookLoanRecordItems")
    @Mapping(target = "bookLoanRecordItems", source = "books")
    BookLoanRecord bookLoanRecordReqDtoToBookLoanRecord(BookLoanRecordDto.Request bookLoanRecordDto);

    @Mapping(target = "id", ignore = true)
    BookLoanRecordDto.Response bookLoanRecordTobookLoanRecordResDto(BookLoanRecord bookLoanRecord);

    BookLoanRecordDto.DetailResponse bookLoanRecordTobookLoanRecordDetailDto(BookLoanRecord bookLoanRecord);

    // @Named("bookLoanRecordItems")
    // default Collection<BookLoanRecordItem>
    // bookLoanRecordItems(Collection<BookLoanRecordItemDto.Request> books) {
    // return books.stream().map((book) -> {
    // BookLoanRecordItem.builder().
    // })
    // }

}