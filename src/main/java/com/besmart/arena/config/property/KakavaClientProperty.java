package com.besmart.arena.config.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@Value
@Builder
@RequiredArgsConstructor
@ConfigurationProperties("kakava-client")
public class KakavaClientProperty {

    @NotNull
    UUID venueId;

    @NotBlank
    String language;
}
