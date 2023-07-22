package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.entity.Librarian;
import com.ufm.library.repository.RoleRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface LibrarianMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookLoanRecords", ignore = true)
    @Mapping(target = "bookReturnRecords", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    Librarian librarianReqDtoToLibrarian(LibrarianDto.Request librarianReqDto, @Context RoleRepository roleRepo);

    @Mapping(target = "role", expression = "java(librarian.getRole().getRoleName().toString())")
    LibrarianDto.Response librarianToLibrarianResDto(Librarian librarian);

    LibrarianDto.CommonField librarianToLibrarianCommomFieldDto(Librarian librarian);

    @AfterMapping
    default void setRoleReference(@MappingTarget Librarian librarian,
            LibrarianDto.Request librarianReqDto,
            @Context RoleRepository roleRepo) {
        var role = roleRepo.getReferenceById(librarianReqDto.getRole());
        librarian.setRole(role);
    }
}
