package com.ufm.library.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.ufm.library.dto.auth.SignInDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.auth.ChangePasswordDto;

public interface AuthController {

    public ResponseEntity<ResponseBody> getProfile();

    public ResponseEntity<ResponseBody> signIn(SignInDto signInDto, HttpServletResponse response);

    public ResponseEntity<ResponseBody> changePassword(Authentication authentication,
            ChangePasswordDto changePasswordDto,
            String authHeader,
            String refreshToken);

    public ResponseEntity<ResponseBody> signOut(String authHeader, String refreshToken);

    public ResponseEntity<ResponseBody> refreshToken(String refreshToken);
}
