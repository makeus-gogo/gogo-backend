package com.gogo.utils.jwt;

public interface TokenService {

    String encodeSignUpToken(Long userId);

    Long decodeSignUpToken(String token);

}
