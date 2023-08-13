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

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.helper.ResponseBodyHelper;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class SecurityExceptionHanlder {

    private final ResponseBodyHelper responseBodyHelper;

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseBody> handleAccessDeniedException(AccessDeniedException ex) {
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
    public ResponseBody handleJwtExpire(AuthenticationException ex) {
        return responseBodyHelper.fail(ex.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBody handleTokenExpiredException(TokenExpiredException ex) {
        return responseBodyHelper.fail("Token của bạn đã hết hạn");
    }

    @ExceptionHandler({ AlgorithmMismatchException.class,
            InvalidClaimException.class,
            JWTDecodeException.class,
            SignatureVerificationException.class, })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBody handleTokenExpiredException(JWTVerificationException ex) {
        return responseBodyHelper.fail("Token của bạn không hợp lệ");
    }

}
