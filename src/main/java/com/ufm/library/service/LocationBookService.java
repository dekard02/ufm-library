package com.ufm.library.service;

import com.ufm.library.dto.LocationBookDto;
import com.ufm.library.dto.api.ResponseBody;

public interface LocationBookService {
    public ResponseBody getAllLocationBooks(Long bookId);

    public ResponseBody getLocationBook(Long bookId, Long locationId);

    public ResponseBody saveLocationBook(Long bookId,
            LocationBookDto.CreateRequest locationBookDto);

    public ResponseBody updateLocationBook(Long bookId, Long locationId,
            LocationBookDto.UpdateRequest locationBookDto);

    // public void deleteLocationBook(Long bookId, Long locationId);
}
