package com.ufm.library.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ufm.library.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.long-expires-time}")
    private Long refreshTokenExpireTime;

    @Value("${jwt.short-expires-time}")
    private Long accessTokenExpireTime;

    private final String ISSUER = "ufm-library";

    private final String ACCESS_TOKEN_TYPE = "access-token";

    private final String REFRESH_TOKEN_TYPE = "refresh-token";

    private String createJwt(String type, String subject, Date expiresAt) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(subject)
                .withClaim("type", type)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    @Override
    public String createRefreshToken(String subject) {
        var expiresAt = new Date(System.currentTimeMillis() + refreshTokenExpireTime * 1000);
        return this.createJwt(REFRESH_TOKEN_TYPE, subject, expiresAt);
    }

    @Override
    public String createAccessToken(String subject) {
        var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpireTime * 1000);
        return this.createJwt(ACCESS_TOKEN_TYPE, subject, expiresAt);
    }

    private JWTVerifier buildVerifier(String type) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .withClaim("type", type)
                .withIssuer(ISSUER)
                .build();
    }

    @Override
    public DecodedJWT verifyAndDecodeAccessToken(String jwt) {
        var decode = buildVerifier(ACCESS_TOKEN_TYPE)
                .verify(jwt);

        return decode;
    }

    @Override
    public DecodedJWT verifyAndDecodeRefreshToken(String jwt) {
        var decode = buildVerifier(REFRESH_TOKEN_TYPE)
                .verify(jwt);
        return decode;
    }

}
