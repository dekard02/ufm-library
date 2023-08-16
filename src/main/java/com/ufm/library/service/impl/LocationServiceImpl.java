package com.ufm.library.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.LocationDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.LocationMapper;
import com.ufm.library.entity.QLocation;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.LocationRepository;
import com.ufm.library.service.LocationService;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepo;
    private final LocationMapper locationMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    public ResponseBody getAllLocations(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QLocation.location.address
                .concat(QLocation.location.name)
                .concat(QLocation.location.phoneNumber)
                .containsIgnoreCase(search)
                .and(predicate);
        var locationPage = locationRepo.findAll(searchPredicate, pageable);
        var locations = locationPage.getContent()
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(locationPage, "locations", locations);
    }

    @Override
    public ResponseBody getLocation(Long id) {
        var locationDto = locationRepo.findById(id)
                .map(locationMapper::locationToLocationDto)
                .orElseThrow(() -> new ApplicationException(
                        "Không tìm thấy cơ sở với mã " + id, HttpStatus.NOT_FOUND));
        return responseBodyHelper.success("location", locationDto);
    }

    @Override
    public ResponseBody saveLocation(LocationDto locationDto) {
        var location = locationMapper.locationDtoToLocation(locationDto);
        location.setId(null);
        locationRepo.save(location);
        return responseBodyHelper
                .success("location", locationMapper.locationToLocationDto(location));
    }

    @Override
    public ResponseBody updateLocation(Long id, LocationDto locationDto) {
        if (!locationRepo.existsById(id)) {
            throw new ApplicationException("Không tìm thấy cơ sở với mã " + id,
                    HttpStatus.NOT_FOUND);
        }
        var location = locationMapper.locationDtoToLocation(locationDto);
        location.setId(id);
        locationRepo.save(location);
        return responseBodyHelper
                .success("location", locationMapper.locationToLocationDto(location));
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepo.findById(id).ifPresentOrElse(
                (location) -> {
                    location.setIsDeleted(true);
                    locationRepo.save(location);
                },
                () -> {
                    throw new ApplicationException("Không tìm thấy cơ sở với mã " + id,
                            HttpStatus.NOT_FOUND);
                });
    }
}