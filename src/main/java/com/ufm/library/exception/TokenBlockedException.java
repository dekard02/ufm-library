package com.ufm.library.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenBlockedException extends JWTVerificationException {

    public TokenBlockedException(String message) {
        super(message);
    }

}
