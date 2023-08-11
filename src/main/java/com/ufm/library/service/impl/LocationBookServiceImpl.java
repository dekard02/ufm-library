package com.ufm.library.service.impl;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.LocationBookMapper;
import com.ufm.library.entity.key.LocationBookKey;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.BookRepository;
import com.ufm.library.repository.LocationBookRepository;
import com.ufm.library.repository.LocationRepository;
import com.ufm.library.service.LocationBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationBookServiceImpl implements LocationBookService {

    private final LocationBookRepository locationBookRepo;
    private final LocationRepository locationRepo;
    private final BookRepository bookRepo;
    private final LocationBookMapper locationBookMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    public ResponseBody getAllLocationBooks(Long bookId) {
        var locationBooks = locationBookRepo.findByBookId(bookId);
        var locationBookDtos = locationBooks.stream()
                .map(locationBookMapper::locationBookToLocationBookDto)
                .collect(Collectors.toList());
        return responseBodyHelper.success("locationBooks", locationBookDtos);
    }

    @Override
    public ResponseBody getLocationBook(Long bookId, Long locationId) {
        var locationBookKey = new LocationBookKey(locationId, bookId);
        var locationBook = locationBookRepo.findById(locationBookKey)
                .map(locationBookMapper::locationBookToLocationBookDto)
                .orElseThrow(
                        () -> new ApplicationException("Không tìm thấy sách ở cơ sở với này", HttpStatus.NOT_FOUND));
        return responseBodyHelper.success("locationBook", locationBook);
    }

    @Override
    public ResponseBody saveLocationBook(Long bookId,
            LocationBookDto.CreateRequest locationBookDto) {
        locationBookDto.setBookId(bookId);

        var locationBookKey = new LocationBookKey(locationBookDto.getLocationId(), bookId);
        locationBookRepo.findById(locationBookKey).ifPresent(
                (locationBook) -> {
                    throw new ApplicationException("Thông tin sách ở cơ sở này đã tồn tại", HttpStatus.BAD_REQUEST);
                });

        var locationBook = locationBookMapper
                .locationBookCreateReqDtoToLocationBook(locationBookDto, bookRepo, locationRepo);
        locationBookRepo.save(locationBook);
        return responseBodyHelper
                .success("locationBook",
                        locationBookMapper.locationBookToLocationBookDto(locationBook));
    }

    @Override
    public ResponseBody updateLocationBook(Long bookId, Long locationId,
            LocationBookDto.UpdateRequest locationBookDto) {
        locationBookDto.setBookId(bookId);
        locationBookDto.setLocationId(locationId);

        var locationBook = locationBookMapper
                .locationBookUpdateReqDtoToLocationBook(locationBookDto, bookRepo, locationRepo);
        locationBookRepo.save(locationBook);
        return responseBodyHelper
                .success("locationBook",
                        locationBookMapper.locationBookToLocationBookDto(locationBook));
    }

    // @Override
    // public void deleteLocationBook(Long bookId, Long locationId) {
    // // throw new HttpRequestMethodNotSupportedException("Http method not support,
    // set quantity to 0 instead");
    // }

}
