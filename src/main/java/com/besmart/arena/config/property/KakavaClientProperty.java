package com.besmart.arena.config.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@ConfigurationProperties("kakava-client")
public final class KakavaClientProperty {

    @NotNull
    private final UUID clientId;

    @NotBlank
    private final String grantType;

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String baseUrl;

    @NotBlank
    private final String authPath;

    @NotBlank
    private final String showsPath;

    @NotBlank
    private final String ticketsPath;

    @NotBlank
    private final String usingTicketPath;
}
