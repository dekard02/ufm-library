package com.ufm.library.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ufm.library.dto.BookLoanRecordItemDto;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.entity.BookLoanRecordItem;
import com.ufm.library.entity.key.LocationBookKey;
import com.ufm.library.repository.LocationBookRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BookLoanRecordItemMapper {
    @Mapping(target = "bookLoanRecord", ignore = true)
    @Mapping(target = "locationBook", ignore = true)
    @Mapping(target = "bookLoanRecordItemKey.locationBookKey.locationId", source = "location")
    @Mapping(target = "bookLoanRecordItemKey.locationBookKey.bookId", source = "book")
    BookLoanRecordItem reqDtoToBookLoanRecord(
            BookLoanRecordItemDto.Request bookLoanRecordDto,
            @Context BookLoanRecord bookLoanRecord,
            @Context LocationBookRepository locationBookRepo);

    @Mapping(target = "bookId", source = "locationBook.book.id")
    @Mapping(target = "bookTitle", source = "locationBook.book.title")
    BookLoanRecordItemDto.Response bookLoanRecordToResDto(BookLoanRecordItem bookLoanRecord);

    @AfterMapping
    default void toEntity(@MappingTarget BookLoanRecordItem bookLoanRecordItem,
            BookLoanRecordItemDto.Request source,
            @Context BookLoanRecord bookLoanRecord,
            @Context LocationBookRepository locationBookRepo) {
        bookLoanRecordItem.setBookLoanRecord(bookLoanRecord);

        var locationId = source.getLocation();
        var bookId = source.getBook();
        var locationBookKey = new LocationBookKey(locationId, bookId);
        var locationBook = locationBookRepo.getReferenceById(locationBookKey);
        bookLoanRecordItem.setLocationBook(locationBook);
    }

}
