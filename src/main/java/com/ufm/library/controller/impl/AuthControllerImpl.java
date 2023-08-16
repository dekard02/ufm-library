package com.ufm.library.controller.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufm.library.controller.AuthController;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.auth.ChangePasswordDto;
import com.ufm.library.dto.auth.SignInDto;
import com.ufm.library.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("profile")
    public ResponseEntity<ResponseBody> getProfile() {
        var responseBody = authService.getProfile();
        return ResponseEntity.ok(responseBody);
    }

    @Override
    @PostMapping("sign-in")
    public ResponseEntity<ResponseBody> signIn(@RequestBody SignInDto signInDto,
            HttpServletResponse response) {
        var responseBody = authService.signIn(signInDto, response);
        return ResponseEntity.ok(responseBody);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping("change-password")
    public ResponseEntity<ResponseBody> changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordDto changePasswordDto,
            @RequestHeader(name = "Authorization", defaultValue = "", required = false) String authHeader,
            @CookieValue(name = "refreshToken", defaultValue = "", required = false) String refreshToken) {
        var accessToken = authHeader.startsWith("Bearer ")
                ? authHeader.split(" ")[1]
                : "";
        var response = authService.changePassword(authentication, changePasswordDto,
                accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping("sign-out")
    public ResponseEntity<ResponseBody> signOut(
            @RequestHeader(name = "Authorization", defaultValue = "", required = false) String authHeader,
            @CookieValue(name = "refreshToken", defaultValue = "", required = false) String refreshToken) {
        var accessToken = authHeader.startsWith("Bearer ")
                ? authHeader.split(" ")[1]
                : "";
        var response = authService.signOut(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping("refresh-token")
    public ResponseEntity<ResponseBody> refreshToken(
            @CookieValue(name = "refreshToken", defaultValue = "", required = false) String refreshToken) {
        var response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

}
