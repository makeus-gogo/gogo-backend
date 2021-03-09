package com.gogo.config.resolver;

import com.gogo.exception.UnAuthorizedException;
import com.gogo.utils.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {

    private final static String BEARER_TOKEN = "Bearer ";

    private final TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isMatchType = parameter.getParameterType().equals(Long.class);
        return hasAnnotation && isMatchType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String header = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new UnAuthorizedException("토큰이 없습니다");
        }
        if (!header.startsWith(BEARER_TOKEN)) {
            throw new UnAuthorizedException(String.format("잘못된 토큰입니다 (%s)", header));
        }
        return tokenService.decodeSignUpToken(header.split(BEARER_TOKEN)[1]);
    }

}
