package com.gogo.external.kakao;

import com.gogo.external.kakao.dto.component.KaKaoAccessTokenComponent;
import com.gogo.external.kakao.dto.component.KaKaoUserInfoComponent;
import com.gogo.external.kakao.dto.response.KaKaoAccessTokenResponse;
import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientKaKaoApiCallerImpl implements KaKaoApiCaller {

    private final WebClient webClient;

    private final KaKaoAccessTokenComponent kaKaoAccessTokenComponent;
    private final KaKaoUserInfoComponent kaKaoUserInfoComponent;

    @Override
    public KaKaoAccessTokenResponse getKaKaoAccessToken(String code, String redirectUri) {
        return webClient.post()
            .uri(kaKaoAccessTokenComponent.getUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(BodyInserters.fromFormData(createKaKaoAccessTokenRequest(code, redirectUri)))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(IllegalArgumentException::new))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(IllegalStateException::new))
            .bodyToMono(KaKaoAccessTokenResponse.class)
            .block();
    }

    private MultiValueMap<String, String> createKaKaoAccessTokenRequest(String code, String redirectUri) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", kaKaoAccessTokenComponent.getClientId());
        formData.add("client_secret", kaKaoAccessTokenComponent.getClientSecret());
        formData.add("grant_type", kaKaoAccessTokenComponent.getGrantType());
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    @Override
    public KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken) {
        return webClient.get()
            .uri(kaKaoUserInfoComponent.getUrl())
            .headers(headers -> headers.setBearerAuth(accessToken))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(IllegalArgumentException::new))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(IllegalStateException::new))
            .bodyToMono(KaKaoUserInfoResponse.class)
            .block();
    }

}
