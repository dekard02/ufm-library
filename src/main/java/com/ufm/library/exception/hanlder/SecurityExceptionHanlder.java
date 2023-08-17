package com.ufm.library.exception.hanlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ufm.library.dto.api.ErrorResponseBody;
import com.ufm.library.exception.TokenBlockedException;
import com.ufm.library.helper.ResponseBodyHelper;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class SecurityExceptionHanlder {

    private final ResponseBodyHelper responseBodyHelper;

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseBody> handleAccessDeniedException(AccessDeniedException ex) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            var response = responseBodyHelper.fail("Hãy đăng nhập để tiếp tục");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            var response = responseBodyHelper.fail("Bạn không có quyền truy cập");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseBody handleJwtExpire(AuthenticationException ex) {
        return responseBodyHelper.fail(ex.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseBody handleTokenExpiredException(JWTVerificationException ex) {
        if (ex instanceof TokenExpiredException) {
            return responseBodyHelper.fail("Token của bạn đã hết hạn");
        }

        if (ex instanceof TokenBlockedException) {
            return responseBodyHelper.fail(ex.getMessage());
        }

        return responseBodyHelper.fail("Token của bạn không hợp lệ");
    }

}
