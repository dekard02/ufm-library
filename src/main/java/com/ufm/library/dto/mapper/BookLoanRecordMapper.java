package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.ufm.library.dto.BookLoanRecordDto;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.StudentRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true), uses = {
        BookLoanRecordItemMapper.class,
        StudentMapper.class,
        LibrarianMapper.class })

public interface BookLoanRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "librarian", ignore = true)
    @Mapping(target = "loanDate", ignore = true)
    @Mapping(target = "bookLoanRecordItems", source = "books")
    BookLoanRecord bookLoanRecordReqDtoToBookLoanRecord(
            BookLoanRecordDto.Request bookLoanRecordDto,
            @Context LibrarianRepository librarianRepo,
            @Context StudentRepository studentRepo);

    @Mapping(target = "books", source = "bookLoanRecordItems")
    @Mapping(target = "librarianId", source = "librarian.id")
    @Mapping(target = "librarianName", source = "librarian.fullname")
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName", source = "student.fullname")
    @Mapping(target = "address", source = "bookLoanRecord", qualifiedByName = "address")
    @Mapping(target = "locationName", source = "bookLoanRecord", qualifiedByName = "locationName")
    BookLoanRecordDto.Response bookLoanRecordTobookLoanRecordResDto(BookLoanRecord bookLoanRecord);

    @Mapping(target = "books", source = "bookLoanRecordItems")
    @Mapping(target = "locationName", source = "bookLoanRecord", qualifiedByName = "locationName")
    @Mapping(target = "address", source = "bookLoanRecord", qualifiedByName = "address")
    BookLoanRecordDto.DetailResponse bookLoanRecordTobookLoanRecordDetailDto(BookLoanRecord bookLoanRecord);

    @AfterMapping
    default void toBookLoanRecord(@MappingTarget BookLoanRecord bookLoanRecord,
            BookLoanRecordDto.Request source,
            @Context LibrarianRepository librarianRepo,
            @Context StudentRepository studentRepo) {
        var librarian = librarianRepo.getReferenceById(source.getLibrarian());
        bookLoanRecord.setLibrarian(librarian);
        var student = studentRepo.getReferenceById(source.getStudent());
        bookLoanRecord.setStudent(student);

        bookLoanRecord.getBookLoanRecordItems().forEach((item) -> {
            var bookLoanRecordId = source.getId();
            item.getBookLoanRecordItemKey().setBookLoanRecordId(bookLoanRecordId);
        });
    }

    @Named("locationName")
    default String locationName(BookLoanRecord bookLoanRecord) {
        var items = bookLoanRecord.getBookLoanRecordItems();
        return items.isEmpty()
                ? null
                : items.get(0).getLocationBook().getLocation().getName();
    }

    @Named("address")
    default String address(BookLoanRecord bookLoanRecord) {
        var items = bookLoanRecord.getBookLoanRecordItems();
        return items.isEmpty()
                ? null
                : items.get(0).getLocationBook().getLocation().getAddress();
    }

}