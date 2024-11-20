package com.besmart.arena.client;

import com.besmart.arena.client.domain.ShowsResponseTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
@RequiredArgsConstructor
public final class KakavaArenaClient implements ArenaClient {
    private static final String VENUES_PARAM_NAME = "venues";
    private static final String GETTING_SHOWS_URL = "https://app-kkv-be-test.azurewebsites.net/api/v1/event/show";
    private static final String LANGUAGE = "EN";
    private static final UUID VENUE_ID = fromString("a60de864-5c52-11ee-a81c-000d3aa868a2");

    private final WebClient webClient;

    @Override
    public ShowsResponseTO request() {
        return webClient.get()
                .uri(buildGettingShowsUri())
                .header(ACCEPT_LANGUAGE, LANGUAGE)
                .retrieve()
                .bodyToMono(ShowsResponseTO.class)
                .block();
    }

    private String buildGettingShowsUri() {
        return fromHttpUrl(GETTING_SHOWS_URL)
                .queryParam(VENUES_PARAM_NAME, singletonList(VENUE_ID))
                .toUriString();
    }
}