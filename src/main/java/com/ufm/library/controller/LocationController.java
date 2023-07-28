package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.LocationDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LocationController {
     public ResponseEntity<ResponseBody> getAllLocations(Predicate predicate, Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getLocation(Long id);

    public ResponseEntity<ResponseBody> createLocation(LocationDto locationDto);

    public ResponseEntity<ResponseBody> updateLocation(Long id, LocationDto locationDto);

    public ResponseEntity<ResponseBody> deleteLocation(Long id);
}
