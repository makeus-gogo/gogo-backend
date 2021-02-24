package com.gogo.utils;

import com.gogo.utils.jwt.TokenService;

public class StubTokenServiceImpl implements TokenService {

    @Override
    public String encodeSignUpToken(Long userId) {
        return "token";
    }

    @Override
    public Long decodeSignUpToken(String token) {
        return 1L;
    }

}
