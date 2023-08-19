package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufm.library.constant.StorageContants;
import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.entity.Librarian;
import com.ufm.library.repository.RoleRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true), uses = RoleMapper.class)
public interface LibrarianMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "role", ignore = true)
    Librarian librarianReqDtoToLibrarian(LibrarianDto.Request librarianReqDto, @Context RoleRepository roleRepo);

    @Mapping(target = "photo", source = "librarian", qualifiedByName = "photo")
    LibrarianDto.Response librarianToLibrarianResDto(Librarian librarian);

    @Mapping(target = "role", expression = "java(librarian.getRole().getRoleName().toString())")
    LibrarianDto.CommonField librarianToLibrarianCommomFieldDto(Librarian librarian);

    @AfterMapping
    default void setRoleReference(@MappingTarget Librarian librarian,
            LibrarianDto.Request librarianReqDto,
            @Context RoleRepository roleRepo) {
        var role = roleRepo.getReferenceById(librarianReqDto.getRole());
        librarian.setRole(role);
    }

    @Named("photo")
    default String photo(Librarian librarian) {
        if (librarian.getPhoto() == null) {
            librarian.setPhoto(StorageContants.LIBRARIAN_DEFAULT_IMAGE);
        }

        if (librarian.getPhoto().startsWith("http")) {
            return librarian.getPhoto();
        }

        var rootUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return rootUrl + "/" + librarian.getPhoto();
    }
}
