package com.gogo.external.kakao.dto.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kakao.auth")
public class KaKaoAccessTokenComponent {

    private String clientId;

    private String clientSecret;

    private String grantType;

    private String url;

}
