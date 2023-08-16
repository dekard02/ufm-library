package com.ufm.library.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {

    public String createRefreshToken(String subject);

    public String createAccessToken(String subject);

    public DecodedJWT verifyAndDecodeAccessToken(String jwt);

    public DecodedJWT verifyAndDecodeRefreshToken(String jwt);

    public void removeJwtFromCache(String jwt);
}
