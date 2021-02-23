package com.gogo.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Uuid {

    private static final String version = "v1";

    @Column(nullable = false)
    private String uuid;

    public Uuid(String uuid) {
        this.uuid = uuid;
    }

    public static Uuid newInstance() {
        return new Uuid(String.format("%s-%s", version, UUID.randomUUID()));
    }

}
