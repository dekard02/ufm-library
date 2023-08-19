package com.ufm.library.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufm.library.dto.BookDto;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.BookPhoto;
import com.ufm.library.repository.AuthorRepository;
import com.ufm.library.repository.CategoryRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true), uses = {
        CategoryMapper.class, LocationBookMapper.class, AuthorMapper.class })
public interface BookMapper {

    @Mapping(target = "photos", qualifiedByName = "photoList")
    BookDto.Response bookToBookResDto(Book book);

    @Mapping(target = "photos", qualifiedByName = "photoList")
    BookDto.Detail bookToBookDetailResDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "locationBooks", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Book bookCreateReqDtoToBook(BookDto.CreateRequest bookCreateRequestDto,
            @Context CategoryRepository categoryRepo,
            @Context AuthorRepository authorRepo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "locationBooks", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Book bookUpdateReqDtoToBook(BookDto.UpdateRequest bookUpdateRequestDto,
            @Context CategoryRepository categoryRepo,
            @Context AuthorRepository authorRepo);

    @Named("photoList")
    default List<String> photoList(List<BookPhoto> photos) {
        return photos
                .stream()
                .map((photo) -> {
                    if (photo.getDirectory().startsWith("http")) {
                        return photo.getDirectory();
                    }

                    var rootUrl = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .build()
                            .toUriString();
                    return rootUrl + "/" + photo.getDirectory();
                })
                .collect(Collectors.toList());
    }

    @AfterMapping
    default void toBook(@MappingTarget Book book,
            BookDto.UpdateRequest bookUpdateRequestDto,
            @Context CategoryRepository categoryRepo,
            @Context AuthorRepository authorRepo) {
        setAuthorAndSetCategories(book,
                bookUpdateRequestDto.getCategories(),
                bookUpdateRequestDto.getAuthor(),
                categoryRepo, authorRepo);
    }

    @AfterMapping
    default void toBook(@MappingTarget Book book,
            BookDto.CreateRequest bookCreateRequestDto,
            @Context CategoryRepository categoryRepo,
            @Context AuthorRepository authorRepo) {
        setAuthorAndSetCategories(book,
                bookCreateRequestDto.getCategories(),
                bookCreateRequestDto.getAuthor(),
                categoryRepo, authorRepo);
    }

    default void setAuthorAndSetCategories(Book book,
            List<Long> categoryIds,
            Long authorId,
            CategoryRepository categoryRepo,
            AuthorRepository authorRepo) {
        var author = authorRepo.getReferenceById(authorId);
        book.setAuthor(author);

        var categories = categoryIds
                .stream()
                .map(categoryRepo::getReferenceById)
                .collect(Collectors.toList());
        book.setCategories(categories);
    }

}
