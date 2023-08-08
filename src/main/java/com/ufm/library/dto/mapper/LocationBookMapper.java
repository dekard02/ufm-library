package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.entity.LocationBook;
import com.ufm.library.repository.BookRepository;
import com.ufm.library.repository.LocationRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface LocationBookMapper {

    @Mapping(source = "locationBookKey.bookId", target = "bookId")
    @Mapping(source = "locationBookKey.locationId", target = "locationId")
    @Mapping(source = "location.address", target = "address")
    @Mapping(source = "location.name", target = "locationName")
    LocationBookDto.Response locationBookToLocationBookDto(LocationBook locationBook);

    @Mapping(source = "bookId", target = "locationBookKey.bookId")
    @Mapping(source = "locationId", target = "locationBookKey.locationId")
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "location", ignore = true)
    LocationBook locationBookDtoToLocationBook(LocationBookDto.Request locationBookReq,
            @Context BookRepository bookRepo,
            @Context LocationRepository locationRepo);

    @Mapping(source = "locationBookKey.bookId", target = "bookId")
    @Mapping(source = "locationBookKey.locationId", target = "locationId")
    LocationBookDto.DetailResponse locationBookToLocationBookDetailDto(LocationBook locationBook);

    @AfterMapping
    default void setReference(
            LocationBookDto.Request locationBookReq,
            @MappingTarget LocationBook locationBook,
            @Context BookRepository bookRepo,
            @Context LocationRepository locationRepo) {
        var book = bookRepo.getReferenceById(locationBookReq.getBookId());
        var location = locationRepo.getReferenceById(locationBookReq.getLocationId());
        locationBook.setBook(book);
        locationBook.setLocation(location);
    }
}
