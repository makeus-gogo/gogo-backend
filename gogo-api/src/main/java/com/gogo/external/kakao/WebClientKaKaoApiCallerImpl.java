package com.gogo.external.kakao;

import com.gogo.external.kakao.dto.component.KaKaoUserInfoComponent;
import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientKaKaoApiCallerImpl implements KaKaoApiCaller {

    private final WebClient webClient;

    private final KaKaoUserInfoComponent kaKaoUserInfoComponent;

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
