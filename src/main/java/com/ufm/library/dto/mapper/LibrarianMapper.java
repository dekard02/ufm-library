package com.ufm.library.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.entity.Librarian;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LibrarianMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "bookLoanRecords", ignore = true)
    @Mapping(target = "bookReturnRecords", ignore = true)
    @Mapping(target = "password", ignore = true)
    Librarian librarianReqDtoToLibrarian(LibrarianDto.Request librarianReqDto);

    @Mapping(target = "role", expression = "java(librarian.role.role.toString())")
    LibrarianDto.Response librarianToLibrarianResDto(Librarian librarian);

    LibrarianDto.CommonField librarianToLibrarianCommomFieldDto(Librarian librarian);

    // @Named("getRoleNameString")
    // default String getRoleNameString(Librarian librarian) {
    // return librarian.getRole().getRole().toString();
    // }
}
