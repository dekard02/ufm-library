package com.ufm.library.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufm.library.controller.LocationBookController;
import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.service.LocationBookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class LocationBookControllerImpl implements LocationBookController {

    private final LocationBookService locationBookService;

    @Override
    @GetMapping("{bookId}/locations")
    public ResponseEntity<ResponseBody> getAllLocationBooks(@PathVariable Long bookId) {
        var response = locationBookService.getAllLocationBooks(bookId);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{bookId}/locations/{locationId}")
    public ResponseEntity<ResponseBody> getLocationBook(@PathVariable Long bookId,
            @PathVariable Long locationId) {
        var response = locationBookService.getLocationBook(bookId, locationId);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("{bookId}/locations")
    public ResponseEntity<ResponseBody> createLocationBook(@PathVariable Long bookId,
            @RequestBody LocationBookDto.CreateRequest locationBookDto) {
        var response = locationBookService.saveLocationBook(bookId, locationBookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{bookId}/locations/{locationId}")
    public ResponseEntity<ResponseBody> updateLocationBook(@PathVariable Long bookId,
            @PathVariable Long locationId,
            @RequestBody LocationBookDto.UpdateRequest locationBookDto) {
        var response = locationBookService.updateLocationBook(bookId, locationId, locationBookDto);
        return ResponseEntity.ok(response);
    }
}
