package com.ufm.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.LocationDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LocationService {
    public ResponseBody getAllLocations(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getLocation(Long id);

    public ResponseBody saveLocation(@Valid LocationDto locationDto);

    public ResponseBody updateLocation(Long id, @Valid LocationDto locationDto);

    public void deleteLocation(Long id);
}
