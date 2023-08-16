package com.ufm.library.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.LocationController;
import com.ufm.library.dto.LocationDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Location;
import com.ufm.library.service.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationControllerImpl implements LocationController {

    private final LocationService locationService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllLocations(
            @QuerydslPredicate(root = Location.class) Predicate predicate,
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = locationService.getAllLocations(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getLocation(@PathVariable Long id) {
        var response = locationService.getLocation(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createLocation(@RequestBody LocationDto locationDto) {
        var response = locationService.saveLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    public ResponseEntity<ResponseBody> updateLocation(@PathVariable Long id,
            @RequestBody LocationDto locationDto) {
        var response = locationService.updateLocation(id, locationDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
    public ResponseEntity<ResponseBody> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

}
