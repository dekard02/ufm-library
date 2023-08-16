package com.ufm.library.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufm.library.controller.NotFoundController;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.helper.ResponseBodyHelper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("**")
@RequiredArgsConstructor
public class NotFoundControllerImpl implements NotFoundController {

    private final ResponseBodyHelper responseBodyHelper;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        var response = responseBodyHelper.fail("Route này không tồn tại");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

}
