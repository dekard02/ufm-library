package com.ufm.library.service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;

import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.auth.ChangePasswordDto;
import com.ufm.library.dto.auth.SignInDto;

public interface AuthService {

    public ResponseBody getProfile();

    public ResponseBody signIn(SignInDto signInDto, HttpServletResponse response);

    public ResponseBody changePassword(Authentication authentication,
            @Valid ChangePasswordDto changePasswordDto,
            String accessToken,
            String refreshToken);

    public ResponseBody signOut(String accessToken, String refreshToken);

    public ResponseBody refreshToken(String refresToken);
}
