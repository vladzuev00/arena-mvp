package com.besmart.arena.client;

import com.besmart.arena.client.domain.ShowsResponseTO;
import com.besmart.arena.config.property.KakavaClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

//TODO: test
@Component
@RequiredArgsConstructor
public final class KakavaArenaClient {
    private static final String VENUES_PARAM_NAME = "venues";
    private static final String GETTING_SHOWS_URL = "https://app-kkv-be-test.azurewebsites.net/api/v1/event/show";

    private final WebClient webClient;
    private final KakavaClientProperty property;

    public ShowsResponseTO requestShows() {
        return webClient.get()
                .uri(buildGettingShowsUri())
                .header(ACCEPT_LANGUAGE, property.getLanguage())
                .retrieve()
                .bodyToMono(ShowsResponseTO.class)
                .block();
    }

    private String buildGettingShowsUri() {
        return fromHttpUrl(GETTING_SHOWS_URL)
                .queryParam(VENUES_PARAM_NAME, singletonList(property.getVenueId()))
                .toUriString();
    }
}
