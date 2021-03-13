package com.gogo.external.google;

import com.gogo.external.google.dto.component.GoogleUserInfoComponent;
import com.gogo.external.google.dto.response.GoogleUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientGoogleApiCallerImpl implements GoogleApiCaller {

    private final WebClient webClient;

    private final GoogleUserInfoComponent googleUserInfoComponent;

    @Override
    public GoogleUserInfoResponse getGoogleUserProfileInfo(String accessToken) {
        return webClient.get()
            .uri(googleUserInfoComponent.getUrl())
            .headers(headers -> headers.setBearerAuth(accessToken))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(IllegalArgumentException::new))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(IllegalStateException::new))
            .bodyToMono(GoogleUserInfoResponse.class)
            .block();
    }

}
