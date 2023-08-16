package com.ufm.library.controller;

import org.springframework.http.ResponseEntity;

import com.ufm.library.dto.api.ResponseBody;

public interface NotFoundController {

    public ResponseEntity<ResponseBody> get();
}
