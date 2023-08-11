package com.ufm.library.controller;

import org.springframework.http.ResponseEntity;

import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LocationBookController {
    public ResponseEntity<ResponseBody> getAllLocationBooks(Long bookId);

    public ResponseEntity<ResponseBody> getLocationBook(Long bookId, Long locationId);

    public ResponseEntity<ResponseBody> createLocationBook(Long bookId,
            LocationBookDto.CreateRequest locationBookDto);

    public ResponseEntity<ResponseBody> updateLocationBook(Long bookId, Long locationId,
            LocationBookDto.UpdateRequest locationBookDto);

    // public ResponseEntity<ResponseBody> deleteLocationBook(Long bookId, Long
    // locationId);
}
