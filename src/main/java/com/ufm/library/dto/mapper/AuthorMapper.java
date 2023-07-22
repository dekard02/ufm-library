package com.ufm.library.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.AuthorDto;
import com.ufm.library.entity.Author;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    AuthorDto authorToAuthorDto(Author author);

    @Mapping(target = "books", ignore = true)
    Author authorDtoToAuthor(AuthorDto authorDto);
}