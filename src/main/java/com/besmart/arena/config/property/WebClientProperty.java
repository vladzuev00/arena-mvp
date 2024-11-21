package com.besmart.arena.config.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@RequiredArgsConstructor
@ConfigurationProperties("web-client")
public class WebClientProperty {

    @NotBlank
    String connectionProviderName;

    @NotNull
    @Positive
    Integer connectTimeoutMs;

    @NotNull
    @Positive
    Integer readTimeoutMs;

    @NotNull
    @Positive
    Integer writeTimeoutMs;

    @NotNull
    @Positive
    Integer maxConnections;

    @NotNull
    @Positive
    Integer pendingAcquireTimeoutMs;

    @NotNull
    @Positive
    Integer maxIdleTimeoutMs;

    @NotNull
    @Positive
    Integer maxLifetimeMs;
}
