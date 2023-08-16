package com.ufm.library.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ufm.library.exception.TokenBlockedException;
import com.ufm.library.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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

    private final RedisTemplate<String, Object> redisTemplate;

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
        var jwt = this.createJwt(REFRESH_TOKEN_TYPE, subject, expiresAt);

        redisTemplate.opsForValue().set(jwt, true, refreshTokenExpireTime, TimeUnit.SECONDS);

        return jwt;
    }

    @Override
    public String createAccessToken(String subject) {
        var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpireTime * 1000);
        var jwt = this.createJwt(ACCESS_TOKEN_TYPE, subject, expiresAt);
        redisTemplate.opsForValue().set(jwt, true, accessTokenExpireTime, TimeUnit.SECONDS);
        return jwt;
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

        if (redisTemplate.opsForValue().get(jwt) == null) {
            throw new TokenBlockedException("Token của bạn đã được đăng xuất");
        }

        return decode;
    }

    @Override
    public DecodedJWT verifyAndDecodeRefreshToken(String jwt) {
        var decode = buildVerifier(REFRESH_TOKEN_TYPE)
                .verify(jwt);

        if (redisTemplate.opsForValue().get(jwt) == null) {
            throw new TokenBlockedException("Token của bạn đã được đăng xuất");
        }

        return decode;
    }

    @Override
    public void removeJwtFromCache(String jwt) {
        redisTemplate.opsForValue().getAndDelete(jwt);
    }

}
