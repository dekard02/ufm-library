package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ufm.library.dto.BookReturnRecordDto;
import com.ufm.library.entity.BookReturnRecord;
import com.ufm.library.entity.Librarian;
import com.ufm.library.repository.BookLoanRecordRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true), uses = {
        LibrarianMapper.class })
public interface BookReturnRecordMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "librarian", ignore = true)
    @Mapping(target = "bookLoanRecord", ignore = true)
    BookReturnRecord bookReturnRecordReqDtoToBookReturnRecord(
            BookReturnRecordDto.Request bookReturnRecordDto,
            @Context Librarian librarian,
            @Context BookLoanRecordRepository bookLoanRecordRepo);

    @Mapping(target = "librarianId", source = "librarian.id")
    @Mapping(target = "librarianName", source = "librarian.fullname")
    @Mapping(target = "bookLoanRecordId", source = "bookLoanRecord.id")
    BookReturnRecordDto.InLoanRecord bookReturnRecordToResDto(BookReturnRecord bookReturnRecord);

    @Mapping(target = "bookLoanRecordId", source = "bookLoanRecord.id")
    BookReturnRecordDto.DetailResponse bookReturnRecordToDetailDto(
            BookReturnRecord bookReturnRecord);

    @AfterMapping
    default void toBookReturnRecord(@MappingTarget BookReturnRecord bookReturnRecord,
            BookReturnRecordDto.Request source,
            @Context Librarian librarian,
            @Context BookLoanRecordRepository bookLoanRecordRepo) {
        bookReturnRecord.setLibrarian(librarian);
        var loanRecord = bookLoanRecordRepo.getReferenceById(source.getBookLoanRecord());
        bookReturnRecord.setBookLoanRecord(loanRecord);
    }
}
