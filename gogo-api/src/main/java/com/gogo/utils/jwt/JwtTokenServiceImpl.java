package com.gogo.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gogo.utils.jwt.dto.component.JwtTokenProviderComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenServiceImpl implements TokenService {

    private final static long expiresMilliSeconds = 60 * 60; // 만료시간: 60분

    private final JwtTokenProviderComponent jwtTokenProviderComponent;

    public String encodeSignUpToken(Long userId) {
        try {
            final ZonedDateTime now = ZonedDateTime.now();
            return JWT.create()
                .withHeader(creatJwtHeader())
                .withIssuer(jwtTokenProviderComponent.getIssuer())
                .withClaim("userId", Double.valueOf(userId))
                .withIssuedAt(Date.from(now.toInstant()))
                .withExpiresAt(Date.from(now.toInstant().plusSeconds(expiresMilliSeconds)))
                .sign(Algorithm.HMAC512(jwtTokenProviderComponent.getSecretKey().getBytes()));
        } catch (JWTCreationException e) {
            throw new IllegalArgumentException(String.format("토큰 생성이 실패하였습니다 (%s)", userId));
        }
    }

    private Map<String, Object> creatJwtHeader() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        return headers;
    }

    public Long decodeSignUpToken(String token) {
        try {
            final DecodedJWT jwt = createJwtVerifier().verify(token);
            return jwt.getClaim("userId").asDouble().longValue();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(String.format("토큰 Decode 에 실패하였습니다 (%s)", token));
        }
    }

    private JWTVerifier createJwtVerifier() {
        return JWT.require(Algorithm.HMAC512(jwtTokenProviderComponent.getSecretKey().getBytes()))
            .withIssuer(jwtTokenProviderComponent.getIssuer())
            .build();
    }

}
