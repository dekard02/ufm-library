package com.ufm.library.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.entity.LocationBook;

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
    @Mapping(target = "bookLoanRecordItems", ignore = true)
    LocationBook locationBookDtoToLocationBook(LocationBookDto.Request locationBookReq);

    @Mapping(source = "locationBookKey.bookId", target = "bookId")
    @Mapping(source = "locationBookKey.locationId", target = "locationId")
    LocationBookDto.DetailResponse locationBookToLocationBookDetailDto(LocationBook locationBook);

}
