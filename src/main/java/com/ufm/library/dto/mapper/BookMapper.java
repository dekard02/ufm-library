package com.ufm.library.dto.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.ufm.library.dto.BookDto;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.BookPhoto;
import com.ufm.library.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    @Mapping(target = "photos", qualifiedByName = "photoList")
    @Mapping(target = "categories", qualifiedByName = "categoryList")
    BookDto.Response bookToBookResDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "locationBooks", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Book bookToBookCreateReqDto(BookDto.CreateRequest bookCreateRequestDto);

    @Named("photoList")
    default Collection<String> photoList(Collection<BookPhoto> photos) {
        return photos
                .stream()
                .map(BookPhoto::getDirectory)
                .collect(Collectors.toList());
    }

    @Named("categoryList")
    default Collection<String> categoryList(Collection<Category> categories) {
        return categories
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
